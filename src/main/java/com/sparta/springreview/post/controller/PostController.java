package com.sparta.springreview.post.controller;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.service.PostService;
import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.response.ResponseDto;
import com.sparta.springreview.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<PostListResponseDto> getAllPosts() {
        PostListResponseDto postResponseDtoList = postService.getAllPosts();
        return ResponseEntity.ok().body(postResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostDetailResponseDto postSimpleResponseDto = postService.createPost(postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(postSimpleResponseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto> getOnePost(@PathVariable Long postId) {
        PostDetailResponseDto postSimpleResponseDto = postService.getOnePost(postId);
        return ResponseEntity.ok().body(postSimpleResponseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        Post post = postService.findPost(postId);
        PostDetailResponseDto postDetailResponseDto = postService.updatePost(post, postRequestDto);
        return ResponseEntity.ok().body(postDetailResponseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        postService.deletePost(post);
        return ResponseEntity.ok().body(
                new ApiResponseDto("게시글 삭제 완료", HttpStatus.OK.value())
        );
    }

    @GetMapping("/search")
    public ResponseEntity<PostListResponseDto> searchPost(@RequestParam String keyword) {
        PostListResponseDto postResponseDtoList = postService.searchPost(keyword);
        return ResponseEntity.ok().body(postResponseDtoList);
    }
}
