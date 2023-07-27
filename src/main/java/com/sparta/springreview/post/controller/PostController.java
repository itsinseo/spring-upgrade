package com.sparta.springreview.post.controller;

import com.sparta.springreview.post.dto.PostResponseDto;
import com.sparta.springreview.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> postResponseDtoList = postService.getPosts();
        return ResponseEntity.ok().body(postResponseDtoList);
    }
}
