package com.sparta.springreview.user.dto;

import com.sparta.springreview.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;

    private String password;

    private UserRoleEnum role;
}
