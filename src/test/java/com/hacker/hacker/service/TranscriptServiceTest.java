package com.hacker.hacker.service;

import com.hacker.hacker.common.ApiResponse;
import com.hacker.hacker.common.response.SuccessMessage;
import com.hacker.hacker.dto.TranscriptByVideoDTO;
import com.hacker.hacker.model.Transcript;
import com.hacker.hacker.model.Video;
import com.hacker.hacker.repository.TranscriptRepository;
import com.hacker.hacker.repository.VideoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TranscriptServiceTest {
    @Mock
    private TranscriptRepository transcriptRepository;

    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private TranscriptService transcriptService;

    @Test
    @DisplayName("동영상별 개별자막 가져오기 테스트")
    void getTranscriptByVideo(){
        //Given
        Video video1 = new Video();
        Video video2 = new Video();
        video1.setVideoTitle("영상 1");
        video2.setVideoTitle("영상 2");
        video1.setVideoId(1L);
        video2.setVideoId(2L);
        videoRepository.save(video1);
        videoRepository.save(video2);

        Transcript transcript11 = new Transcript();
        Transcript transcript12 = new Transcript();
        Transcript transcript13 = new Transcript();
        Transcript transcript21 = new Transcript();
        Transcript transcript22 = new Transcript();
        Transcript transcript23 = new Transcript();
        transcript11.setVideo(video1);
        transcript12.setVideo(video1);
        transcript13.setVideo(video1);
        transcript21.setVideo(video2);
        transcript22.setVideo(video2);
        transcript23.setVideo(video2);
        transcript11.setTranscriptId(1L);
        transcript12.setTranscriptId(2L);
        transcript13.setTranscriptId(3L);
        transcript21.setTranscriptId(4L);
        transcript22.setTranscriptId(5L);
        transcript23.setTranscriptId(6L);
        transcript11.setSentence("abc");
        transcript12.setSentence("def");
        transcript13.setSentence("ghi");
        transcript21.setSentence("jkl");
        transcript22.setSentence("mno");
        transcript23.setSentence("pqr");
        transcriptRepository.save(transcript11);
        transcriptRepository.save(transcript12);
        transcriptRepository.save(transcript13);
        transcriptRepository.save(transcript21);
        transcriptRepository.save(transcript22);
        transcriptRepository.save(transcript23);

        when(transcriptRepository.findById(3L)).thenReturn(Optional.of(transcript13));
        //When
        //ApiResponse<List<TranscriptByVideoDTO>> response = transcriptService.getTranscriptByVideo(1L, 3L);

        //Then
        //assertEquals(200, response.getStatus());
        //assertEquals(SuccessMessage.TRANSCRIPT_BY_VIDEO_GET_SUCCESS.getMessage(), response.getMessage());
        //assertEquals(1, response.getData().size());
        //assertEquals("ghi", response.getData().get(0).getSentence());

    }
}