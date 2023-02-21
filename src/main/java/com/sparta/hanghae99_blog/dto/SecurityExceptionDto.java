package com.sparta.hanghae99_blog.dto;

public class SecurityExceptionDto {
    private int statusCode;
    private String msg;

    public SecurityExceptionDto(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
