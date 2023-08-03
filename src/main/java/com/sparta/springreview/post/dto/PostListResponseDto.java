package com.sparta.springreview.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponseDto {
    private final List<PostSimpleResponseDto> postList;

    public PostListResponseDto(List<PostSimpleResponseDto> postListResponseDtoList) {
        this.postList = postListResponseDtoList;
    }
}
