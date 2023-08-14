package com.sparta.springreview.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentRequestDto {
    @Schema(description = "댓글내용", defaultValue = "commentContent")
    private String content;

    @Builder
    public CommentRequestDto(String content) {
        this.content = content;
    }
}
