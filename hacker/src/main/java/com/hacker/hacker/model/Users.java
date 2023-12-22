package com.hacker.hacker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long userId;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    @NotNull
    private String email;

    @Column
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column
    private String social;

    @Column
    private String profileImageUrl;

    @Column(length = 1500)
    private String introduce;

    @Column
    private Long totalScore = 0L;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<UserVideo> userVideos = new HashSet<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Evaluation> evaluations = new HashSet<>();

}
