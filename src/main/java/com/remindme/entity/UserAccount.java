package com.remindme.entity;
import com.remindme.security.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name", nullable = false)
    @NonNull
    private String userName;
    @Column(name = "password", nullable = false)
    @NonNull
    private String password;
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "created_at",nullable = false)
    private OffsetDateTime createdAt;
    @OneToMany(
            mappedBy = "userAccount",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Friend> friends=new ArrayList<>();
    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    @Column
    private boolean hasTestedText=false;


}
