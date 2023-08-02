package com.sparta.springreview.post.dto;

import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PostSimpleResponseDto implements ResponseDto {
    private Long postId;
    private String title;
    private String nickname;
    private String createdAt;

    public PostSimpleResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
