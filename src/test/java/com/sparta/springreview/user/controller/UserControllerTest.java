package com.sparta.springreview.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springreview.user.dto.SignupRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private static final String BASE_URL = "/api/user";

    @Test
    @Transactional
    void signup() throws Exception {
        // given
        String username = "testuser1";
        String password = "password1";
        String passwordConfirm = "password1";

        // when
        String body = objectMapper.writeValueAsString(
                SignupRequestDto.builder()
                        .username(username)
                        .password(password)
                        .passwordConfirm(passwordConfirm)
                        .build()
        );

        // then
        mockMvc.perform(post(BASE_URL + "/signup")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("회원가입 성공"))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.OK.value()));
    }
}