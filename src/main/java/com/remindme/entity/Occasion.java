package com.remindme.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "occasion")
public class Occasion {
    @Id
    @SequenceGenerator(
            name = "occasion_sequence",
            sequenceName = "occasion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "occasion_sequence"
    )
    private Long id;
    private String occasionName;
    private LocalDate occasionDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(
            name = "friend_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey (
                    name = "friend_id_fk"
            )
    )
    Friend friend;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "occasion",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Text>texts=new ArrayList<>();

}
