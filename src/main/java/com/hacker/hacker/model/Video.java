package com.hacker.hacker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hacker.hacker.dto.VideoUploadDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="video_id")
    private Long videoId;

    @Column
    private String link;

    @Column
    private String videoTitle;

    @Column
    private String creator;

    @Column
    private Long duration;

    @Column
    private Boolean isDefault;

    @Column
    private Long views;

    @Column
    private Long youtubeViews;

    @Column
    @CreatedDate
    private Date createdAt;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<CategoryVideo> categoryVideos = new HashSet<>();

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<UserVideo> userVideos = new HashSet<>();

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private Set<Transcript> transcripts = new HashSet<>();

    public static Video toModel(VideoUploadDTO dto){
        return Video.builder()
                .videoTitle(dto.getTitle())
                .creator(dto.getCreator())
                .duration(dto.getDuration())
                .isDefault(true)
                .views(0L)
                .build();
    }
}
