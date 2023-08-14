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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment Controller", description = "댓글 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @Operation(summary = "댓글 생성", description = "게시글에 댓글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createComment(@RequestParam Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Post post = postService.findPost(postId);
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto, userDetails.getUser(), post);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    @Operation(summary = "댓글 조회", description = "댓글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/{commentId}")
    public ResponseEntity<ResponseDto> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        return ResponseEntity.ok().body(new CommentResponseDto(comment));
    }

    @Operation(summary = "댓글 수정", description = "댓글 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        CommentResponseDto commentResponseDto = commentService.updateComment(comment, commentRequestDto);
        return ResponseEntity.ok().body(commentResponseDto);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long commentId) {
        Comment comment = commentService.findComment(commentId);
        commentService.deleteComment(comment);
        return ResponseEntity.ok().body(
                new ApiResponseDto("댓글 삭제 완료", HttpStatus.OK.value())
        );
    }
}
