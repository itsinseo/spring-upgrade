package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.dto.PostSimpleResponseDto;
import com.sparta.springreview.user.entity.User;

public interface PostService {
    /**
     * 전체 게시글 목록 조회
     * @return  전체 게시글 목록
     */
    PostListResponseDto getAllPosts();

    /**
     * 게시글 작성
     * @param postRequestDto    게시글 작성 내용
     * @param user              사용자 정보
     * @return 작성된 게시글
     */
    PostSimpleResponseDto createPost(PostRequestDto postRequestDto, User user);

    /**
     * 게시글 단건 조회
     *
     * @param postId 요청 게시글 ID
     * @return 요청 게시글
     */
    PostDetailResponseDto getOnePost(Long postId);
}
