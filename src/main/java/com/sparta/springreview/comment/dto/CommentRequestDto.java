package com.sparta.springreview.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentRequestDto {
    private String content;

    @Builder
    public CommentRequestDto(String content) {
        this.content = content;
    }
}
