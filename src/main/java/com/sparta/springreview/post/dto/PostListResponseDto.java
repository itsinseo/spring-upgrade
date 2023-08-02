package com.sparta.springreview.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponseDto {
    private List<PostSimpleResponseDto> postSimpleResponseDtoList;

    public PostListResponseDto(List<PostSimpleResponseDto> postListResponseDtoList) {
        this.postSimpleResponseDtoList = postListResponseDtoList;
    }
}
