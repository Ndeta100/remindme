package com.remindme.service;

import com.remindme.entity.UserProfile;
import com.remindme.exception.NamingConflictException;
import com.remindme.exception.NotFoundException;
import com.remindme.repository.UserProfileRepository;
import com.remindme.validation.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService{
    UserProfileRepository userProfileRepository;
    UserAccountService userAccountService;
    Validation validation=new Validation();


    @Override
    public List<UserProfile> getUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Override
    public void addNewUserProfile(UserProfile userProfile) {
        UserProfile conflictUserEmail=validation.checkEmailConflict(userProfile.getEmail(),userProfileRepository);
        UserProfile conflictUserPhone=validation.checkPhoneConflict(userProfile.getPhoneNumber(),userProfileRepository);
        if(conflictUserPhone==null && conflictUserEmail==null) {
            userProfileRepository.save(userProfile);
        }else if (conflictUserPhone !=null && conflictUserEmail==null) {
            throw new NamingConflictException("phone number " + userProfile.getPhoneNumber() + "is already registered to another user");

        } else if (conflictUserPhone == null) {
            throw  new NamingConflictException("Email " + userProfile.getEmail() + "is already registered to another user");
        }else {
            throw new NamingConflictException("Email " + userProfile.getEmail() + "and phone number " +
                    userProfile.getPhoneNumber());
        }
    }

    @Override
    public UserProfile getUserProfileById(Long id) {
        return userProfileRepository
                .findById(id)
                .orElseThrow(
                        ()->{
                            return new NotFoundException("User with id " + id + "not found");
                        }
                );
    }

    @Override
    public UserProfile getUserProfileByEmail(String email) {
        return validation.checkEmail(email, userProfileRepository);
    }

    @Override
    public UserProfile getUserProfileByPhoneNumber(String phoneNumber) {
        return validation.checkPhone(phoneNumber, userProfileRepository);
    }

    @Override
    public void deleteUserProfileById(Long Id) {
        Optional<UserProfile> userOptional=userProfileRepository
                .findById(Id);
        if(userOptional.isPresent()) {
            userProfileRepository.deleteById(Id);
        }
        throw new NotFoundException(("User with " + Id + "does not exist"));
    }

    @Override
    public void deleteUserProfileByEmail(String email) {
        userProfileRepository.deleteById(validation.checkEmail(email, userProfileRepository).getId());
    }

    @Override
    public void updateUserProfileEmail(String email, String newEmail) {
       UserProfile currentUser=validation.checkEmail(email, userProfileRepository);
       UserProfile conflictUser=validation.checkEmailConflict(newEmail, userProfileRepository);
       if(conflictUser !=null) {
           throw new NamingConflictException("Sorry, your email is already taken");
       }else {
           currentUser.setEmail(newEmail);
           userProfileRepository.save(currentUser);
       }
    }

    @Override
    public void updateUserProfilePhoneNumber(String phoneNumber, String newPhoneNumber) {
        UserProfile currentUser=validation.checkPhone(phoneNumber, userProfileRepository);
        UserProfile conflictUser=validation.checkPhoneConflict(newPhoneNumber, userProfileRepository);
        if(conflictUser !=null) {
            throw new NamingConflictException("Sorry, phone number is already taken");
        }else {
            currentUser.setPhoneNumber(newPhoneNumber);
            userProfileRepository.save(currentUser);
        }
    }

    @Override
    public void updateUserProfileFirstName(String email, String newFirstName) {
        UserProfile currentUser=validation.checkEmail(email, userProfileRepository);
        currentUser.setFirstName(newFirstName);
        userProfileRepository.save(currentUser);

    }


    @Override
    public void updateUserProfileLastName(String email, String newLastName) {
        UserProfile currentUser=validation.checkEmail(email, userProfileRepository);
        currentUser.setLastName(newLastName);
        userProfileRepository.save(currentUser);
    }

    @Override
    public void updateUserProfileUserName(String email, String newUserName) {
            UserProfile currentUser=validation.checkEmail(email, userProfileRepository);
            userAccountService.updateUserName(currentUser.getFirstName().toLowerCase(),newUserName);
    }

    @Override
    public void updateUserProfile(String newEmail, String newPhoneNumber, String newFirstName, String newLastName, String newUserName, String currentEmail) {
        UserProfile userProfile=getUserProfileByEmail(currentEmail);
        if(!newEmail.equals("")){
            updateUserProfileEmail(userProfile.getEmail(),newEmail);
        }
        if (!newPhoneNumber.equals("")) {
            updateUserProfilePhoneNumber(userProfile.getPhoneNumber(),newPhoneNumber);
        }
        if (!newFirstName.equals("")) {
            updateUserProfileFirstName(userProfile.getEmail(),newFirstName);
        }
        if (!newLastName.equals("")) {
            updateUserProfileLastName(userProfile.getEmail(),newLastName);
        }
        if (!newUserName.equals("")) {
            updateUserProfileUserName(userProfile.getEmail(),newUserName);
        }
    }
}
