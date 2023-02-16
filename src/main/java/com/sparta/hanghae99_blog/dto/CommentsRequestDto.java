package com.sparta.hanghae99_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsRequestDto {

    private String comment;

    public CommentsRequestDto(String comment) {
        this.comment = comment;
    }
}
