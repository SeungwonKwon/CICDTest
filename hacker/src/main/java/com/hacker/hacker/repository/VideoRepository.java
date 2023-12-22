package com.hacker.hacker.repository;

import com.hacker.hacker.model.Video;
import com.hacker.hacker.service.VideoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface VideoRepository extends JpaRepository<Video, Long> {
}
