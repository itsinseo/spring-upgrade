package com.sparta.springreview.post.dto;

import com.sparta.springreview.comment.dto.CommentResponseDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.response.SimpleDate;
import lombok.Getter;

import java.util.List;

@Getter
public class PostDetailResponseDto extends PostSimpleResponseDto {
    private final String modifiedAt;
    private final String content;
    private final List<CommentResponseDto> commentList;

    public PostDetailResponseDto(Post post) {
        super(post);
        this.modifiedAt = SimpleDate.simpleDate(post.getModifiedAt());
        this.content = post.getContent();
        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new).toList();
    }
}
