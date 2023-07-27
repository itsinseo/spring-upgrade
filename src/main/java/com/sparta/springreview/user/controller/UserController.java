package com.sparta.springreview.user.controller;

import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.user.dto.SignupRequestDto;
import com.sparta.springreview.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
//    private final JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // 회원가입 닉네임, 비밀번호 패턴 예외처리
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage());
                stringBuilder.append("\n");
            }
            String patternErrorMessage = stringBuilder.toString();
            return ResponseEntity.badRequest().body(new ApiResponseDto(patternErrorMessage, HttpStatus.BAD_REQUEST.value()));
        }

        try {
            ApiResponseDto apiResponseDto = userService.signup(signupRequestDto);
            return ResponseEntity.ok().body(apiResponseDto);
        } catch (IllegalArgumentException e) { // 중복 닉네임 예외처리
            return ResponseEntity.badRequest().body(new ApiResponseDto("회원가입 오류: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        } catch (Exception e) { // 서버 예외처리(코드 잘못 작성 등)
            return ResponseEntity.internalServerError().body(new ApiResponseDto("서버 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

//    @ResponseBody
//    @PostMapping("/signin")
//    public ResponseEntity<ApiResponseDto> signin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        try {
//            ApiResponseDto apiResponseDto = userService.signin(loginRequestDto);
//
//            //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
//            String token = jwtUtil.createToken(loginRequestDto.getUsername(), loginRequestDto.getRole());
//
//            Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, UriEncoder.encode(token));
//            cookie.setPath("/");
//            response.addCookie(cookie);
//
//            return ResponseEntity.ok().body(apiResponseDto);
//        } catch (IllegalArgumentException e) { // 로그인 정보 불일치 예외처리
//            return ResponseEntity.badRequest().body(new ApiResponseDto("회원가입 오류: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
//        } catch (Exception e) { // 서버 예외처리(코드 잘못 작성 등)
//            return ResponseEntity.badRequest().body(new ApiResponseDto("서버 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        }
//    }
}
