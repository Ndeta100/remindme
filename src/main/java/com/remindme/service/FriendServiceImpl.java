package com.remindme.service;

import com.remindme.entity.Friend;
import com.remindme.entity.Occasion;
import com.remindme.entity.UserAccount;
import com.remindme.exception.BadInputException;
import com.remindme.exception.NamingConflictException;
import com.remindme.repository.FriendRepository;
import com.remindme.validation.Validation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Service
public class FriendServiceImpl implements FriendService{
    FriendRepository friendRepository;
    OccasionService occasionService;
    private final Validation validation=new Validation();
    @Override
    public void addNewFriend(UserAccount userAccount, Friend friend) {
            Friend confictFriend= validation.checkFriendConflict(userAccount
            ,friend,friendRepository);
            if(!validation.isValidPhone(friend.getPhoneNumber())){
                throw new BadInputException(friend.getPhoneNumber() + " is not a valid phone number");
            }
            if(confictFriend==null){
                friend.setUserAccount(userAccount);
                friendRepository.save(friend);
                Occasion birthday=new Occasion("Birthday", friend.getDob());
                occasionService.addOccasion(friend.getId(),birthday);
            }else {
                throw new NamingConflictException("");
            }
    }

    @Override
    public void deleteFriendById(Long friendId) {
            friendRepository.deleteById(validation.checkFriend(friendId, friendRepository).getId());
    }

    @Override
    public void updateFriend(String newFriendPhoneNumber,
                             String newFriendFirstName,
                             String newFriendLastName, String newFriendDOB,
                             Long friendId) {
        if (!newFriendPhoneNumber.equals("")) {
            updateFriendPhoneNumber(newFriendPhoneNumber,friendId);
        }
        if (!newFriendFirstName.equals("")){
            updateFriendFirstName(newFriendFirstName,friendId);
        }
        if(!newFriendDOB.equals("")){
            updateFriendDOB(LocalDate.parse(newFriendDOB),friendId);
        }
        if (!newFriendLastName.equals("")){
            updateFriendLastName(newFriendLastName,friendId);
        }

    }


    @Override
    public void updateFriendPhoneNumber(String newPhoneNumber, Long friendId) {
        Friend friend=validation.checkFriend(friendId,friendRepository);
        if(validation.isValidPhone(newPhoneNumber)){
            friend.setPhoneNumber(newPhoneNumber);
            friendRepository.save(friend);
        }else {
            throw new BadInputException(newPhoneNumber + " is not a valid phone number");
        }
    }

    @Override
    public void updateFriendFirstName(String newFirstName, Long friendId) {
        Friend friend=validation.checkFriend(friendId, friendRepository);
        friend.setFirstName(newFirstName);
        friendRepository.save(friend);
    }

    @Override
    public void updateFriendLastName(String newLastName, Long friendId) {
        Friend friend=validation.checkFriend(friendId, friendRepository);
        friend.setLastName(newLastName);
        friendRepository.save(friend);
    }

    @Override
    public void updateFriendDOB(LocalDate newFriendDOB, Long friendId) {
        Friend friend=validation.checkFriend(friendId, friendRepository);
        friend.setDob(newFriendDOB);
        friendRepository.save(friend);
    }

    @Override
    public Friend getFriendById(Long friendId) {
        return validation.checkFriend(friendId,friendRepository);
    }

    @Override
    public List<Friend> getFriendsByUser(String userName) {
        return null;
    }
}
