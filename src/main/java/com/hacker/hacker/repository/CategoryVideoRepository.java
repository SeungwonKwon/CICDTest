package com.hacker.hacker.repository;

import com.hacker.hacker.model.CategoryVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CategoryVideoRepository extends JpaRepository<CategoryVideo, Long> {
    List<CategoryVideo> findByCategory_CategoryId(Long categoryId);
}
