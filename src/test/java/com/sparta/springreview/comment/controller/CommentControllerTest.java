package com.sparta.springreview.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springreview.WithMockCustomUser;
import com.sparta.springreview.comment.dto.CommentRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL = "/api/comment";
    private static final String NOT_EXPECTED_EXCEPTION = "예상된 예외가 아닙니다.";

    @Test
    @WithMockCustomUser
    @Transactional
    void createComment() throws Exception {
        // given
        String content = "testComment";

        // when
        String body = objectMapper.writeValueAsString(
                CommentRequestDto.builder()
                        .content(content)
                        .build()
        );

        // then
        mockMvc.perform(post(BASE_URL)
                        .param("postId", String.valueOf(1))
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content));
    }

    @Test
    void getComment() throws Exception {
        // given
        Long commentId = 1L;

        // when

        // then
        mockMvc.perform(get(BASE_URL + "/{commentId}", commentId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void updateComment() throws Exception {
        // given
        String content = "testComment";

        // when
        String body = objectMapper.writeValueAsString(
                CommentRequestDto.builder()
                        .content(content)
                        .build()
        );

        // then
        mockMvc.perform(put(BASE_URL + "/{commentId}", 1)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof RejectedExecutionException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                });
    }

    @Test
    @WithMockCustomUser
    @Transactional
    void deleteComment() throws Exception {
        // given

        // when

        // then
        mockMvc.perform(delete(BASE_URL + "/{commentId}", 1)
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof RejectedExecutionException)) {
                        throw new AssertionError(NOT_EXPECTED_EXCEPTION);
                    }
                });
    }
}