package com.hacker.hacker.controller;

import com.hacker.hacker.common.ApiResponse;
import com.hacker.hacker.dto.TranscriptByVideoDTO;
import com.hacker.hacker.service.TranscriptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TranscriptController {
    private final TranscriptService transcriptService;

    public TranscriptController(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    @GetMapping("videos/{videoId}/transcripts/{transcriptId}")
    public ApiResponse<TranscriptByVideoDTO> getTranscriptByVideo(
            @PathVariable Long videoId, @PathVariable Long transcriptId
    ){
        return transcriptService.getTranscriptByVideo(videoId, transcriptId);
    }
}
