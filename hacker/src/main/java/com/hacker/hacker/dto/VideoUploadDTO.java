package com.hacker.hacker.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoUploadDTO {
    private Long category;
    private String creator;
    private Long duration;
    private String title;
}
