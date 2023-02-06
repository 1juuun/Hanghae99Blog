package com.sparta.hanghae99_blog.entity;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

}
