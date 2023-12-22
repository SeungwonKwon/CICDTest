package com.hacker.hacker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacker.hacker.common.ApiResponse;
import com.hacker.hacker.common.response.SuccessMessage;
import com.hacker.hacker.dto.*;
import com.hacker.hacker.model.CategoryVideo;
import com.hacker.hacker.model.Transcript;
import com.hacker.hacker.model.Video;
import com.hacker.hacker.repository.CategoryVideoRepository;
import com.hacker.hacker.repository.TranscriptRepository;
import com.hacker.hacker.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VideoService {
    private final CategoryVideoRepository categoryVideoRepository;
    private final WebClient webClient;
    private final VideoRepository videoRepository;
    private final TranscriptRepository transcriptRepository;
    ExchangeStrategies strategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
            .build();

    public VideoService(CategoryVideoRepository categoryVideoRepository, VideoRepository videoRepository, TranscriptRepository transcriptRepository) {
        this.categoryVideoRepository = categoryVideoRepository;
        this.transcriptRepository = transcriptRepository;
        this.webClient = WebClient.builder()
                .exchangeStrategies(strategies)
                .build();
        this.videoRepository = videoRepository;
    }

    public ApiResponse<List<VideosByCategoryDTO>> getVideosByCategoryIds(List<Long> categoryIds){
        List<VideosByCategoryDTO> videosByCategoryDTOList = new ArrayList<>();
        for(Long categoryId : categoryIds){
            VideosByCategoryDTO videosByCategoryDTO = new VideosByCategoryDTO();
            videosByCategoryDTO.setCategoryId(categoryId);
            Set<VideoDTO> videoDTOS = new HashSet<>();
            List<CategoryVideo> categoryVideos = categoryVideoRepository.findByCategory_CategoryId(categoryId);
            for(CategoryVideo categoryVideo : categoryVideos){
                VideoDTO videoDTO = new VideoDTO();
                videoDTO.setVideoId(categoryVideo.getVideo().getVideoId());
                videoDTO.setVideoUrl(categoryVideo.getVideo().getLink());
                videoDTOS.add(videoDTO);
            }
            videosByCategoryDTO.setVideos(videoDTOS);
            videosByCategoryDTOList.add(videosByCategoryDTO);
        }
        return ApiResponse.success(SuccessMessage.VIDEOS_BY_CATEGORY_GET_SUCCESS, videosByCategoryDTOList);
    }

    public ApiResponse<List<VideoListDTO>> getAllVideos(){
        List<Video> videoList = videoRepository.findAll();
        List<VideoListDTO> videoListDTOS = new ArrayList<>();
        for(Video video : videoList){
            VideoListDTO videoListDTO = new VideoListDTO();
            videoListDTO.setVideoTitle(video.getVideoTitle());
            videoListDTO.setVideoId(video.getVideoId());
            videoListDTOS.add(videoListDTO);
        }
        return ApiResponse.success(SuccessMessage.GET_ALL_VIDEOS_SUCCESS,videoListDTOS);
    }

    public ApiResponse<VideoUploadDTO> newVideo(String link) throws ParseException, JsonProcessingException {
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("http://localhost:8000")
                        .build();
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        Map<String, Object> response = webClient
                        .get()
                        .uri(uriBuilder ->
                                uriBuilder
                                        .path("/getYoutubeData/"+link)
                                        .build())
                        .retrieve()
                        .bodyToMono(Map.class)
                        .block();

        VideoUploadDTO videoUploadDTO = new VideoUploadDTO();
        videoUploadDTO.setCategory(Long.valueOf(response.get("category").toString()));
        videoUploadDTO.setCreator((response.get("creator").toString()));
        videoUploadDTO.setDuration((Long.valueOf(response.get("duration").toString())));
        videoUploadDTO.setTitle(response.get("title").toString());

        Video video = Video.toModel(videoUploadDTO);
        video.setLink("https://www.youtube.com/watch?v=" + link);
        video.setYoutubeViews(Long.valueOf(response.get("viewCount").toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        video.setCreatedAt(formatter.parse(response.get("createdAt").toString()));
        videoRepository.save(video);

        Object transcriptInput = response.get("transcripts");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> transcripts = objectMapper.convertValue(transcriptInput, List.class);
        for(Map<String, Object> transcriptData : transcripts) {
            Transcript transcript = new Transcript();
            transcript.setDuration(Double.valueOf(transcriptData.get("duration").toString()));
            transcript.setStart(Double.valueOf(transcriptData.get("start").toString()));
            transcript.setSentence(transcriptData.get("text").toString());
            transcript.setSoundLink("abc");
            transcript.setVideo(video);
            transcriptRepository.save(transcript);
        }
        return ApiResponse.success(SuccessMessage.POST_NEW_VIDEO_SUCCESS, videoUploadDTO);
    }
}
