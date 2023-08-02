package com.sparta.springreview.post.dto;

import com.sparta.springreview.post.entity.Post;
import lombok.Getter;

@Getter
public class PostDetailResponseDto extends PostSimpleResponseDto {
    private String content;

    public PostDetailResponseDto(Post post) {
        super(post);
        this.content = post.getContent();
    }
}
