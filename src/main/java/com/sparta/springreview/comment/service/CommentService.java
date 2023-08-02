package com.sparta.springreview.comment.service;

import com.sparta.springreview.comment.dto.CommentRequestDto;
import com.sparta.springreview.comment.dto.CommentResponseDto;
import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.user.entity.User;

public interface CommentService {
    CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user, Post post);

    CommentResponseDto updateComment(Comment comment, CommentRequestDto commentRequestDto);

    void deleteComment(Comment comment);

    Comment findComment(Long commentId);
}
