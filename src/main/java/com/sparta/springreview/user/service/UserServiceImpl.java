package com.sparta.springreview.user.service;

import com.sparta.springreview.response.ApiResponseDto;
import com.sparta.springreview.user.dto.SigninRequestDto;
import com.sparta.springreview.user.dto.SignupRequestDto;
import com.sparta.springreview.user.entity.User;
import com.sparta.springreview.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordConfirm = requestDto.getPasswordConfirm();
        if (password.contains(username)) {
            throw new IllegalArgumentException("회원가입 실패: 비밀번호는 닉네임을 포함해선 안됩니다.");
        }

        if (!passwordConfirm.equals(password)) {
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", HttpStatus.OK.value());
    }

    @Override
    public ApiResponseDto signin(SigninRequestDto signinRequestDto) {
        String username = signinRequestDto.getUsername();
        String password = signinRequestDto.getPassword();

        //사용자 확인 (username 이 없는 경우)
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인 (password 가 다른 경우)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new ApiResponseDto("로그인 성공", HttpStatus.OK.value());
    }
}
