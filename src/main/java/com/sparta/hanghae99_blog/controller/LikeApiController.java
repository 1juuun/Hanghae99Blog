package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.MessageDto;
import com.sparta.hanghae99_blog.security.UserDetailsImpl;
import com.sparta.hanghae99_blog.service.CommentsLikeService;
import com.sparta.hanghae99_blog.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeApiController {

    // 좋아요
    private final PostLikeService postLikeService;
    // 댓글 좋아요
    private final CommentsLikeService commentsLikeService;

    // 좋아요 표시
    @PostMapping("/posts/{post_id}")
    public ResponseEntity<MessageDto> postLike(@PathVariable Long post_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(postLikeService.likeOrDislike(post_id, userDetails.getUser()));
    }
    @PostMapping("/comments/{comments_id}")
    public ResponseEntity<MessageDto> commentsLike(@PathVariable Long comments_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(commentsLikeService.likeOrDislike(comments_id, userDetails.getUser()));
    }

}
