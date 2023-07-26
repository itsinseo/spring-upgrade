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
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("/user/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
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
            ApiResponseDto response = userService.signup(signupRequestDto);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("회원가입 오류: " + e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("서버 오류: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
