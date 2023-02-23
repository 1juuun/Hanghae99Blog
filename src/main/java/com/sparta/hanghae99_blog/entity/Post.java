package com.sparta.hanghae99_blog.entity;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;
    @Column(nullable = false)
    private Integer likeCount = 0;
    @ManyToOne
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
    }

    public void update(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
