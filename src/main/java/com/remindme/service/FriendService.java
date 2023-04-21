package com.remindme.service;

import com.remindme.entity.Friend;
import com.remindme.entity.UserAccount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
public interface FriendService {
    public void addNewFriend(UserAccount userAccount, Friend friend);
    public void deleteFriendById(Long friendId);
    public void updateFriend(String newFriendPhoneNumber,
                             String newFriendFirstName,
                             String newFriendLastName,
                             String newFriendDOB,
                             Long friendId);
    @Transactional
    public void updateFriendPhoneNumber(String newPhoneNumber, Long friendId);
    @Transactional
    public void updateFriendFirstName(String newFirstName, Long friendId);
    @Transactional
    public void updateFriendLastName(String newLastName, Long friendId);
    @Transactional
    public void updateFriendDOB(LocalDate newFriendDOB, Long friendId);
    public Friend getFriendById(Long friendId);
    public List<Friend> getFriendsByUser(String userName);
    public  List<Friend> getFriendsByUserId(Long userId);
}
