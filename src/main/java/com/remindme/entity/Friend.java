package com.remindme.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "friend")
public class Friend {
    @Id
    @Column(unique = true,updatable = false,nullable = false)
    private Long id;
    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(nullable = true)
    private String phoneNumber;
    @Column(nullable = true)
    private LocalDate dob;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(
            name = "user_profile_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey (
                    name = "user_profile_id_fk"
            )
    )
    UserAccount userAccount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(
            mappedBy = "friend",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Occasion> occasions=new ArrayList<>();
}
