package com.sparta.springreview.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springreview.WithMockCustomUser;
import com.sparta.springreview.post.dto.PostRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL = "/api/post";
    private static final String NOT_EXPECTED_EXCEPTION = "예상된 예외가 아닙니다.";

    @Test
    void getAllPosts() throws Exception {
        // given
        int page = 0;
        int size = 5;

        // when
        Pageable pageable = PageRequest.of(page, size);

        // then
        mockMvc.perform(get(BASE_URL)
                        .param("page", String.valueOf(pageable.getOffset()))
                        .param("size", String.valueOf(pageable.getPageSize()))
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void createPost() throws Exception {
        // given
        String title = "testTitle";
        String content = "testContent";

        // when
        String body = objectMapper.writeValueAsString(
                PostRequestDto.builder()
                        .title(title)
                        .content(content)
                        .build());

        // then
        mockMvc.perform(post(BASE_URL)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @Test
    void getOnePost() throws Exception {
        // given
        int page = 0;
        int size = 5;

        // when
        Pageable pageable = PageRequest.of(page, size);

        // then: 올바른 postId 요청 시
        mockMvc.perform(get(BASE_URL + "/{postId}", 1)
                        .param("page", String.valueOf(pageable.getOffset()))
                        .param("size", String.valueOf(pageable.getPageSize()))
                )
                .andExpect(status().isOk());

        // then: 잘못된 postId 요청 시
        mockMvc.perform(get(BASE_URL + "/{postId}", 0)
                        .param("page", String.valueOf(pageable.getOffset()))
                        .param("size", String.valueOf(pageable.getPageSize()))
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof IllegalArgumentException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                })
                .andExpect(jsonPath("$.message").value("해당 ID의 게시글이 존재하지 않습니다."));
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void updatePost() throws Exception {
        // given
        String title = "testUpdateTitle";
        String content = "testUpdateContent";

        // when
        String body = objectMapper.writeValueAsString(
                PostRequestDto.builder()
                        .title(title)
                        .content(content)
                        .build()
        );

        // then: 잘못된 postID 요청 시
        mockMvc.perform(put(BASE_URL + "/{postId}", 0)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof IllegalArgumentException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                })
                .andExpect(jsonPath("$.message").value("해당 ID의 게시글이 존재하지 않습니다."));

        // then 2: 게시글 작성자가 아닐 시
        mockMvc.perform(put(BASE_URL + "/{postId}", 1)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof RejectedExecutionException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                })
                .andExpect(jsonPath("$.message").value("작성자만 게시글을 수정/삭제할 수 있습니다."));
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void deletePost() throws Exception {
        // then: 잘못된 postID 요청 시
        mockMvc.perform(delete(BASE_URL + "/{postId}", 0)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof IllegalArgumentException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                })
                .andExpect(jsonPath("$.message").value("해당 ID의 게시글이 존재하지 않습니다."));

        // then 2: 게시글 작성자가 아닐 시
        mockMvc.perform(delete(BASE_URL + "/{postId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof RejectedExecutionException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                })
                .andExpect(jsonPath("$.message").value("작성자만 게시글을 수정/삭제할 수 있습니다."));
    }

    @Test
    void searchPost() throws Exception {
        // given
        String keyword = "게시글";
        int page = 0;
        int size = 5;

        // when
        Pageable pageable = PageRequest.of(page, size);

        // then
        mockMvc.perform(get(BASE_URL + "/search")
                        .param("keyword", keyword)
                        .param("page", String.valueOf(pageable.getOffset()))
                        .param("size", String.valueOf(pageable.getPageSize()))
                )
                .andExpect(status().isOk());
    }
}