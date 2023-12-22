package com.hacker.hacker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table
public class Examples {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="example_id")
    private Long exampleId;

    @Column
    private String sentence;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    @JsonBackReference
    private Word word;
}
