package com.sparta.springreview.comment.dto;

import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.response.ResponseDto;
import com.sparta.springreview.response.SimpleDate;
import lombok.Getter;

@Getter
public class CommentResponseDto implements ResponseDto {
    private final Long commentId;
    private final String content;
    private final String nickname;
    private final String createdAt;
    private final String modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.nickname = comment.getUser().getUsername();
        this.createdAt = SimpleDate.simpleDate(comment.getCreatedAt());
        this.modifiedAt = SimpleDate.simpleDate(comment.getModifiedAt());
    }
}
