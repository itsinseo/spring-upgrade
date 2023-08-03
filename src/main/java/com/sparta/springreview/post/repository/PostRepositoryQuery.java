package com.sparta.springreview.post.repository;

import com.sparta.springreview.post.entity.Post;

import java.util.List;

public interface PostRepositoryQuery {
    List<Post> search(String keyword);
}
