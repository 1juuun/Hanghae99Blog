package com.sparta.hanghae99_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageDto {

    private String message;
    private int errorCode;

    public MessageDto(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
