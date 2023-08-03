package com.sparta.springreview.aop;

import com.sparta.springreview.exception.PatternMismatchException;
import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.RejectedExecutionException;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({IllegalArgumentException.class, RejectedExecutionException.class, PatternMismatchException.class})
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        return ResponseEntity.badRequest().body(
                new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }
}
