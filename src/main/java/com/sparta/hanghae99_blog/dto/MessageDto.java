package com.sparta.hanghae99_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageDto {

    private String msg;
    private int statusCode;

    public MessageDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
