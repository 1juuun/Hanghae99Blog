package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.LoginRequestDto;
import com.sparta.hanghae99_blog.dto.SignupRequestDto;
import com.sparta.hanghae99_blog.entity.User;
import com.sparta.hanghae99_blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);

        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 이름입니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }

    public void login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

    }
}
