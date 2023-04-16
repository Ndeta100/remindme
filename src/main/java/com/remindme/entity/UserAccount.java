package com.remindme.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remindme.security.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonManagedReference
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "id")
    @MapsId
    private UserProfile userProfile;

    @Column(name = "user_name", nullable = false)
    @NonNull
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    @NonNull
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    public UserAccount(UserProfile userProfile){
        this.userProfile=userProfile;
    }


    public UserAccount() {

    }
}
