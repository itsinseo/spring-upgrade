package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.user.entity.User;

public interface PostService {
    /**
     * 전체 게시글 목록 조회
     *
     * @return 전체 게시글 목록
     */
    PostListResponseDto getAllPosts();

    /**
     * 게시글 작성
     *
     * @param postRequestDto 게시글 작성 내용
     * @param user           사용자 정보
     * @return 작성된 게시글
     */
    PostDetailResponseDto createPost(PostRequestDto postRequestDto, User user);

    /**
     * 게시글 단건 조회
     *
     * @param postId 요청 게시글 ID
     * @return 요청 게시글
     */
    PostDetailResponseDto getOnePost(Long postId);

    /**
     * 게시글 Entity 조회
     *
     * @param postId 조회할 게시글 ID
     * @return 조회한 게시글
     */
    Post findPost(Long postId);

    /**
     * 게시글 수정
     *
     * @param post           수정할 게시글
     * @param postRequestDto 수정할 게시글 내용
     * @return 수정된 게시글
     */
    PostDetailResponseDto updatePost(Post post, PostRequestDto postRequestDto);

    /**
     * 게시글 삭제
     *
     * @param post 삭제할 게시글
     */
    void deletePost(Post post);
}
