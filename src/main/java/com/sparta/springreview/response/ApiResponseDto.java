package com.sparta.springreview.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponseDto {
    private final String message;
    private final Integer statusCode;

}