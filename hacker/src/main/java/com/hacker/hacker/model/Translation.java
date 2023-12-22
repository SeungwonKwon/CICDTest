package com.hacker.hacker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="translation_id")
    private Long translationId;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "transcript_id", nullable = false)
    @JsonBackReference
    private Transcript transcript;
}
