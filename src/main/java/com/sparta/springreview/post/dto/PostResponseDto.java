package com.sparta.springreview.post.dto;

import com.sparta.springreview.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private String title;
    private String nickname;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.nickname = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
    }
}
