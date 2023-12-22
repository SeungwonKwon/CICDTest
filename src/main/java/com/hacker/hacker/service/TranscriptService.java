package com.hacker.hacker.service;

import com.hacker.hacker.common.ApiResponse;
import com.hacker.hacker.common.response.ErrorMessage;
import com.hacker.hacker.common.response.SuccessMessage;
import com.hacker.hacker.dto.TranscriptByVideoDTO;
import com.hacker.hacker.model.Transcript;
import com.hacker.hacker.model.Video;
import com.hacker.hacker.repository.TranscriptRepository;
import com.hacker.hacker.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TranscriptService {
    private final TranscriptRepository transcriptRepository;
    private final VideoRepository videoRepository;

    public TranscriptService(TranscriptRepository transcriptRepository, VideoRepository videoRepository) {
        this.transcriptRepository = transcriptRepository;
        this.videoRepository = videoRepository;
    }

    public ApiResponse<TranscriptByVideoDTO> getTranscriptByVideo(Long videoId, Long transcriptId){
        Transcript transcript = transcriptRepository.findById(transcriptId).get();
        TranscriptByVideoDTO transcriptByVideoDTO = new TranscriptByVideoDTO();
        transcriptByVideoDTO.setVideoId(transcript.getVideo().getVideoId());
        transcriptByVideoDTO.setSentence(transcript.getSentence());
        transcriptByVideoDTO.setDuration(transcript.getDuration());
        transcriptByVideoDTO.setStart(transcript.getStart());
        return ApiResponse.success(SuccessMessage.TRANSCRIPT_BY_VIDEO_GET_SUCCESS, transcriptByVideoDTO);
    }
}
