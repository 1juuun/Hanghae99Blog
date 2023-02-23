package com.sparta.hanghae99_blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommentsLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "COMMENTS_ID", nullable = false)
    private Comments comments;

    public CommentsLike(User user, Comments comments) {
        this.user = user;
        this.comments = comments;
    }
}
