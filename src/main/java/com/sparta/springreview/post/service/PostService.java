package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    /**
     *
     * @return  전체 게시글 목록 조회
     */
    List<PostResponseDto> getPosts();
}
