package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.dto.PostResponseDto;
import com.sparta.springreview.user.entity.User;

import java.util.List;

public interface PostService {
    /**
     * 전체 게시글 목록 조회
     * @return  전체 게시글 목록
     */
    List<PostResponseDto> getAllPosts();

    /**
     * 게시글 작성
     * @param postRequestDto    게시글 작성 내용
     * @param user              사용자 정보
     * @return 작성된 게시글
     */
    PostResponseDto createPost(PostRequestDto postRequestDto, User user);
}
