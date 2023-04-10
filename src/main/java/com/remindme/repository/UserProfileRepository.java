package com.remindme.repository;

import com.remindme.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile>findUserProfileByEmail(String email);
    Optional<UserProfile>findUserProfileByPhoneNumber(String phoneNumber);
}
