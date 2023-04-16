package com.remindme.service;

import com.remindme.entity.UserAccount;
import com.remindme.repository.UserAccountRepository;
import com.remindme.repository.UserProfileRepository;
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

    @Override
    public void addNewUserAccount(UserAccount userAccount) {

    }

    @Override
    public UserAccount getUserAccountByUserName(String userName) {
        return null;
    }

    @Override
    public boolean validatePassword(String userName, String password) {
        return false;
    }

    @Override
    public void updateUserName(String userName, String newUserName) {

    }

    @Override
    public void updatePassword(String userName, String password) {

    }

    @Override
    public void sentTestText(String userName, String message) throws Exception {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserAccount userAccount=
        return null;
    }
}
