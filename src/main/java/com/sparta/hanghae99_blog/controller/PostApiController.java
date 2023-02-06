package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/poston")
    public Post savePost(@RequestBody PostRequestDto requestDto) {

       return postService.save(requestDto);
    }

    @GetMapping("/api/posts")
    public List<Post> getPost() {
        return postService.getPosts();
    }

}
