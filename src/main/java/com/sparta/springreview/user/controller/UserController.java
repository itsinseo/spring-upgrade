package com.sparta.springreview.user.controller;

import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.response.ResponseDto;
import com.sparta.springreview.user.dto.SignupRequestDto;
import com.sparta.springreview.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Controller", description = "유저 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
//    private final JwtUtil jwtUtil;

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "bad_request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        ApiResponseDto apiResponseDto = userService.signup(signupRequestDto);
        return ResponseEntity.ok().body(apiResponseDto);
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
