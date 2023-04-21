package com.remindme.service;

import com.remindme.entity.MyUserDetails;
import com.remindme.entity.UserAccount;
import com.remindme.exception.BadInputException;
import com.remindme.exception.NamingConflictException;
import com.remindme.repository.UserAccountRepository;
import com.remindme.repository.UserProfileRepository;
import com.remindme.twilio.SmsRequest;
import com.remindme.twilio.TwilioSender;
import com.remindme.validation.Validation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {
    UserAccountRepository userAccountRepository;
    UserProfileRepository userProfileRepository;
    Validation validation=new Validation();
    PasswordEncoder passwordEncoder;
    private final TwilioSender twilioSender;

    @Override
    public void addNewUserAccount(UserAccount userAccount) {
        UserAccount conflictUserAccount=validation.checkUserNameConflict(userAccount.getUserName(),
                userAccountRepository);
        if(conflictUserAccount==null) {
            userAccountRepository.save(userAccount);
        }else {
            throw new NamingConflictException(
                    "Username " + userAccount.getUserName() + " is already taken by another account"
            );
        }
    }

    @Override
    public UserAccount getUserAccountByUserName(String userName) {
        return validation.checkUserName(userName,userAccountRepository);
    }

    @Override
    public boolean validatePassword(String userName, String password) {
        if (passwordEncoder.matches(password,getUserAccountByUserName(userName).getPassword())){
            return true;
        }else {
            throw new BadInputException("Incorrect password");
        }
    }

    @Override
    public void updateUserName(String userName, String newUserName) {
        UserAccount userAccount=getUserAccountByUserName(userName);
        userAccount.setUserName(newUserName);
        userAccountRepository.save(userAccount);

    }

    @Override
    public void updatePassword(String userName, String password) {
        UserAccount userAccount=getUserAccountByUserName(userName);
        userAccount.setPassword(password);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void sentTestText(String userName, String message) throws Exception {
        UserAccount userAccount=getUserAccountByUserName(userName);
        if(!userAccount.isHasTestedText()){
            SmsRequest smsRequest=new SmsRequest(userAccount.getUserProfile().getPhoneNumber(), message);
            twilioSender.sendSms(smsRequest);
            userAccount.setHasTestedText(true);
            userAccountRepository.save(userAccount);
        }else {
            throw new Exception("Text has already been sent");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount=userAccountRepository
                .findUserAccountByUserName(username)
                .orElseThrow(
                        ()->
                                new UsernameNotFoundException(String.format("Username %s not found",username))
                );
        return new MyUserDetails(userAccount);
    }
}
