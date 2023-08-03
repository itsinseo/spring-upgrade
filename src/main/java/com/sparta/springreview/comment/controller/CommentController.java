package com.sparta.springreview.comment.controller;

import com.sparta.springreview.comment.dto.CommentRequestDto;
import com.sparta.springreview.comment.dto.CommentResponseDto;
import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.comment.service.CommentService;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.service.PostService;
import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.response.ResponseDto;
import com.sparta.springreview.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ResponseDto> createComment(@RequestParam Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Post post = postService.findPost(postId);
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto, userDetails.getUser(), post);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseDto> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        return ResponseEntity.ok().body(new CommentResponseDto(comment));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        CommentResponseDto commentResponseDto = commentService.updateComment(comment, commentRequestDto);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        commentService.deleteComment(comment);
        return ResponseEntity.ok().body(
                new ApiResponseDto("댓글 삭제 완료", HttpStatus.OK.value())
        );
    }
}
