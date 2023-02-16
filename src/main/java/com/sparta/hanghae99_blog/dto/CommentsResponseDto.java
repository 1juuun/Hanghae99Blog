package com.sparta.hanghae99_blog.dto;

import com.sparta.hanghae99_blog.entity.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentsResponseDto {

    private Long id;
    private String username;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifedAt;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.username = comments.getUser().getUsername();
        this.comments = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifedAt = comments.getModifedAt();
    }
}
