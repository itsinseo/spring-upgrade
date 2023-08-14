package com.sparta.springreview.user.service;

import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.user.dto.SignupRequestDto;
import com.sparta.springreview.user.entity.User;
import com.sparta.springreview.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceMock;

    @Test
    void signup() {
        // given
        SignupRequestDto signupRequestDto =
                SignupRequestDto.builder()
                        .username("username1")
                        .password("pass1234")
                        .passwordConfirm("pass1234")
                        .build();

        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user); // save 메소드 호출 시 가짜 동작 설정

        // when
        ApiResponseDto apiResponseDto = userServiceMock.signup(signupRequestDto);

        // then
        assertThat(apiResponseDto.getMessage()).isEqualTo("회원가입 성공");
    }

    @Test
    void signin() {
    }
}