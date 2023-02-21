package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.dto.PostResponseDto;

import com.sparta.hanghae99_blog.security.UserDetailsImpl;
import com.sparta.hanghae99_blog.service.PostService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostService postService;

    // 게시물 등록
    @PostMapping("/on")
    public ResponseEntity<PostResponseDto> savePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return ResponseEntity.ok().body(postService.save(requestDto, userDetails.getUser()));
    }

    // 게시물 전체 조회
    @GetMapping("/lists")
    public List<PostResponseDto> getPost() {
        return postService.getPosts();
    }

    // 해당 게시물 조회
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPostByID(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    // 해당 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(
            @PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(postService.update(id, requestDto, userDetails.getUser()));
    }

    // 해당 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(
            @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(postService.deletePost(id, userDetails.getUser()));
    }

}
