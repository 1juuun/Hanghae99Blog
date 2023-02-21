package com.sparta.hanghae99_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;

    public PostRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
