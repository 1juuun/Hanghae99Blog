package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.dto.PostResponseDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/api/posts/{id}")
    public PostResponseDto getPostByID(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("api/posts/{id}")
    public boolean updatePost(@PathVariable Long id, String password, @RequestBody PostRequestDto requestDto) {

        if(!password.equals(requestDto.getPassword())) {
            return false;
        }

        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public boolean deletePost(@PathVariable Long id, String password) {

        PostRequestDto requestDto = new PostRequestDto();

        if(!password.equals(requestDto.getPassword())) {
            return false;
        }

        return postService.deletePost(id);
    }

}
