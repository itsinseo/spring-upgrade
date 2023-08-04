package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.user.entity.User;
import org.springframework.data.domain.Pageable;

public interface PostService {
    /**
     * 페이지 전체 게시물 조회
     *
     * @param pageable 조회할 페이지, 크기
     * @return 조회한 게시글 목록
     */
    PostListResponseDto getAllPosts(Pageable pageable);

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

    /**
     * 게시글 검색
     *
     * @param keyword  검색할 키워드(제목)
     * @param pageable 페이징
     * @return 검색된 게시글 목록
     */
    PostListResponseDto searchPost(String keyword, Pageable pageable);

    /**
     * 게시글 Entity 조회
     *
     * @param postId 조회할 게시글 ID
     * @return 조회한 게시글
     */
    Post findPost(Long postId);
}
