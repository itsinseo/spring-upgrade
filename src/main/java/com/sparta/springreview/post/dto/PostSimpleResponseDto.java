package com.sparta.springreview.post.dto;

import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.response.ResponseDto;
import com.sparta.springreview.response.SimpleDate;
import lombok.Getter;

@Getter
public class PostSimpleResponseDto implements ResponseDto {
    private final Long postId;
    private final String title;
    private final String nickname;
    private final String createdAt;

    public PostSimpleResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.nickname = post.getUser().getUsername();
        this.createdAt = SimpleDate.simpleDate(post.getCreatedAt());
    }
}
