package com.sparta.hanghae99_blog.entity;

import com.sparta.hanghae99_blog.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
    public User(SignupRequestDto signupRequestDto, UserRoleEnum role) {
        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();
        this.role = role;
    }

}
