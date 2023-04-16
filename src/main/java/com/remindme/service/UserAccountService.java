package com.remindme.service;

import com.remindme.entity.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserAccountService  {
    public void addNewUserAccount(UserAccount userAccount);
    public UserAccount getUserAccountByUserName(String userName);
    public boolean validatePassword(String userName, String password);
    @Transactional
    public void updateUserName(String userName, String newUserName);
    @Transactional
    public void updatePassword(String userName, String password);
    public void sentTestText(String userName, String message)throws Exception;
}
