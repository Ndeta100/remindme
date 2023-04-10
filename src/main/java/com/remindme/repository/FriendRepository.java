package com.remindme.repository;

import com.remindme.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    Optional<Friend> findFriendByUserAccountIdAndPhoneNumber(Long userAccountId, String
                                                              friendPhoneNumber);
}
