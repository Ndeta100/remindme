package com.remindme.service;

import com.remindme.entity.UserProfile;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProfileService {
    List<UserProfile> getUserProfiles();
    @Transactional
    public void addNewUserProfile(UserProfile userProfile);
    @Transactional
    public UserProfile getUserProfileById(Long id);
    public UserProfile getUserProfileByEmail(String email);
    public UserProfile getUserProfileByPhoneNumber(String phoneNumber);
    public void deleteUserProfileById(Long Id);
    @Transactional
    public void deleteUserProfileByEmail(String email);
    @Transactional
    public void updateUserProfileEmail(String email, String newEmail);
    @Transactional
    public void updateUserProfilePhoneNumber(String phoneNumber, String newPhoneNumber);
    @Transactional
    public void updateUserProfileFirstName(String email, String newFirstName);
    @Transactional
    public void updateUserProfileLastName(String email, String newLastName);
    @Transactional
    public void updateUserProfileUserName(String email, String newUserName);

    public void updateUserProfile(String newEmail, String newPhoneNumber, String newFirstName, String newLastName, String newUserName, String currentEmail);
}
