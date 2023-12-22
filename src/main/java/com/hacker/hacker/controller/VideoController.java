package com.hacker.hacker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hacker.hacker.common.ApiResponse;
import com.hacker.hacker.dto.VideoListDTO;
import com.hacker.hacker.dto.VideoUploadDTO;
import com.hacker.hacker.dto.VideosByCategoryDTO;
import com.hacker.hacker.model.CategoryVideo;
import com.hacker.hacker.service.VideoService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos/categories")
    public ApiResponse<List<VideosByCategoryDTO>> getVideosByCategoryIds(@RequestParam List<Long> categoryId){
        return videoService.getVideosByCategoryIds(categoryId);

    }

    @GetMapping("/videos")
    public ApiResponse<List<VideoListDTO>> getAllVideos(){
        return videoService.getAllVideos();

    }

    @RequestMapping(value = "/newVideos/{link}", method =  RequestMethod.GET)
    public ApiResponse<VideoUploadDTO> newVideo(@PathVariable String link) throws ParseException, JsonProcessingException {
        return videoService.newVideo(link);

    }
}
