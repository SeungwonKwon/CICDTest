package com.hacker.hacker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptByVideoDTO {
    private long videoId;
    private long transcriptId;
    private String sentence;
    private Double start;
    private Double duration;
}
