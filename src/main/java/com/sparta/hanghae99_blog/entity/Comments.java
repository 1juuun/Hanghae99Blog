package com.sparta.hanghae99_blog.entity;

import com.sparta.hanghae99_blog.dto.CommentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comments extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comments(CommentsRequestDto commentsRequestDto, Post post, User user) {
        this.comment = commentsRequestDto.getComment();
        this.post = post;
        this.user = user;
    }

    public void update(Long id, CommentsRequestDto commentsRequestDto, User user) {
        this.id = id;
        this.comment = commentsRequestDto.getComment();
        this.user = user;
    }

}
