package com.sparta.springreview.comment.service;

import com.sparta.springreview.comment.dto.CommentRequestDto;
import com.sparta.springreview.comment.dto.CommentResponseDto;
import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.comment.repository.CommentRepository;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user, Post post) {
        Comment comment = new Comment(commentRequestDto.getContent(), user, post);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
