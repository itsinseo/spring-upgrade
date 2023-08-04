package com.sparta.springreview.comment.service;

import com.sparta.springreview.comment.dto.CommentRequestDto;
import com.sparta.springreview.comment.dto.CommentResponseDto;
import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.comment.repository.CommentRepository;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public CommentResponseDto updateComment(Comment comment, CommentRequestDto commentRequestDto) {
        comment.setContent(commentRequestDto.getContent());
        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 댓글이 존재하지 않습니다.")
        );
    }
}
