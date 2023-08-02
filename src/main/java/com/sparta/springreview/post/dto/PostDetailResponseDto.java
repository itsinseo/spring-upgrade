package com.sparta.springreview.post.dto;

import com.sparta.springreview.post.entity.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class PostDetailResponseDto extends PostSimpleResponseDto {
    private String modifiedAt;
    private String content;

    public PostDetailResponseDto(Post post) {
        super(post);
        this.modifiedAt = post.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.content = post.getContent();
    }
}
