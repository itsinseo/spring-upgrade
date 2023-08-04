package com.sparta.springreview.post.repository;

import com.sparta.springreview.post.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryQuery {
    List<Post> search(String keyword, Pageable pageable);
}
