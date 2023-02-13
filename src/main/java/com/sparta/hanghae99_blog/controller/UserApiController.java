package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.LoginRequestDto;
import com.sparta.hanghae99_blog.dto.MessageDto;
import com.sparta.hanghae99_blog.dto.SignupRequestDto;
import com.sparta.hanghae99_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/signup")
    public MessageDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new MessageDto("회원가입 성공!", 200);
    }

    // 로그인 API
    @PostMapping("/login")
    public MessageDto login(@RequestBody LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return new MessageDto("로그인 성공", 200);
    }


}
