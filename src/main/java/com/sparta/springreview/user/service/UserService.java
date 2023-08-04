package com.sparta.springreview.user.service;

import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.user.dto.SigninRequestDto;
import com.sparta.springreview.user.dto.SignupRequestDto;

public interface UserService {
    /**
     * 회원가입
     * @param requestDto    회원가입 요청 정보
     * @return              요청 처리 결과 메세지 + 상태 코드
     */
    ApiResponseDto signup(SignupRequestDto requestDto);

    /**
     * 로그인 - AuthenticationFilter(UsernamePasswordFilter)로 대체
     * @param signinRequestDto   로그인 요청 정보
     * @return                  로그인 요청 처리 결과 메세지 + 상태 코드
     */
    ApiResponseDto signin(SigninRequestDto signinRequestDto);
}
