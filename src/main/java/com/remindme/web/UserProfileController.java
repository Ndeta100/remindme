package com.remindme.web;

import com.remindme.entity.UserProfile;
import com.remindme.jwt.JwtConfig;
import com.remindme.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/remindme/profile")
public class UserProfileController {
        UserProfileService userProfileService;
        JwtConfig jwtConfig;
        SecretKey secretKey;

            @PostMapping(path = "addUser")
            public void addUserProfile(
                    @RequestParam(name = "firstName") String firstName,
                    @RequestParam(name = "lastName")String lastName,
                    @RequestParam(name = "email") String email,
                    @RequestParam(name = "phoneNumber") String phoneNumber
            ){
                UserProfile newUserProfile=new UserProfile(
                        UUID.randomUUID(),
                        firstName,
                        lastName,
                        phoneNumber,
                        OffsetDateTime.now()
                );
                userProfileService.addNewUserProfile(newUserProfile);
            }

}
