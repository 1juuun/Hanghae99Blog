package com.sparta.hanghae99_blog.dto;

import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private List<CommentsResponseDto> commentsList;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
        this.commentsList = getCommentsList();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifedAt = post.getModifedAt();
    }

    public PostResponseDto(Post post, List<CommentsResponseDto> commentsResponseDtoList) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
        this.commentsList = commentsResponseDtoList;
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifedAt = post.getModifedAt();
    }

    public void update(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
