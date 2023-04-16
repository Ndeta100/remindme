package com.remindme.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, updatable = false)
    private Long id;
    @Column(name = "first_name",nullable = false)
    @NonNull
    private String firstName;
    @Column(name = "last_name",nullable = false)
    @NonNull
    private String lastName;
    @Column(name = "email",nullable = false, unique = true)
    @NonNull
    private String email;
    @Column(name = "phone_number",nullable = false, unique = true)
    private String phoneNumber;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;


    public UserProfile() {

    }
}
