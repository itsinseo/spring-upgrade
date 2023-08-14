package com.sparta.springreview.post.controller;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post Controller", description = "게시글 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Operation(summary = "모든 게시글 조회", description = "모든 게시글을 페이징하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<PostListResponseDto> getAllPosts(Pageable pageable) {
        PostListResponseDto postResponseDtoList = postService.getAllPosts(pageable);
        return ResponseEntity.ok().body(postResponseDtoList);
    }

    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostDetailResponseDto postDetailResponseDto = postService.createPost(postRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(postDetailResponseDto);
    }

    @Operation(summary = "게시글 단건 조회", description = "게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto> getOnePost(@PathVariable Long postId) {
        PostDetailResponseDto postDetailResponseDto = postService.getOnePost(postId);
        return ResponseEntity.ok().body(postDetailResponseDto);
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @PutMapping("/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        Post post = postService.findPost(postId);
        PostDetailResponseDto postDetailResponseDto = postService.updatePost(post, postRequestDto);
        return ResponseEntity.ok().body(postDetailResponseDto);
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long postId) {
        Post post = postService.findPost(postId);
        postService.deletePost(post);
        return ResponseEntity.ok().body(
                new ApiResponseDto("게시글 삭제 완료", HttpStatus.OK.value())
        );
    }

    @Operation(summary = "게시글 검색", description = "키워드로 게시글을 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping("/search")
    public ResponseEntity<PostListResponseDto> searchPost(@RequestParam String keyword, Pageable pageable) {
        PostListResponseDto postResponseDtoList = postService.searchPost(keyword, pageable);
        return ResponseEntity.ok().body(postResponseDtoList);
    }
}
