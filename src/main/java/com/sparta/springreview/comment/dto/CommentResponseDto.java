package com.sparta.springreview.comment.dto;

import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.response.ResponseDto;
import com.sparta.springreview.response.SimpleDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto implements ResponseDto {
    @Schema(description = "댓글 ID")
    private final Long commentId;
    @Schema(description = "댓글 내용")
    private final String content;
    @Schema(description = "댓글 작성자 닉네임")
    private final String nickname;
    @Schema(description = "작성 시각")
    private final String createdAt;
    @Schema(description = "수정 시각")
    private final String modifiedAt;

    @Builder
    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.nickname = comment.getUser().getUsername();
        this.createdAt = SimpleDate.simpleDate(comment.getCreatedAt());
        this.modifiedAt = SimpleDate.simpleDate(comment.getModifiedAt());
    }
}
