package com.sparta.hanghae99_blog.dto;

import com.sparta.hanghae99_blog.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String name;
    private String title;
    private String contents;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.name = post.getName();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }


}
