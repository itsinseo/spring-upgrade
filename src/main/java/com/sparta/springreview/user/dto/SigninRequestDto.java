package com.sparta.springreview.user.dto;

import com.sparta.springreview.user.entity.UserRoleEnum;
import lombok.*;




@NoArgsConstructor
@Getter
public class SigninRequestDto {
    private String username;

    private String password;

    private UserRoleEnum role;

    @Builder
    public SigninRequestDto(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
