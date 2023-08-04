package com.sparta.springreview.user.dto;

import com.sparta.springreview.user.entity.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SigninRequestDto {
    private String username;

    private String password;

    private UserRoleEnum role;
}
