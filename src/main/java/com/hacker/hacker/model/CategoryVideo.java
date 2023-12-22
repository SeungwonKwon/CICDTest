package com.hacker.hacker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table
public class CategoryVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryvideo_id")
    private Long categoryVideoId;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    @JsonBackReference
    private Video video;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;
}
