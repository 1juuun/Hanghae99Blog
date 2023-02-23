package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.CommentsRequestDto;
import com.sparta.hanghae99_blog.security.UserDetailsImpl;
import com.sparta.hanghae99_blog.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsApiController {

    private final CommentsService commentsService;

    // 댓글 저장
    @PostMapping("/on/{post_id}")
    public ResponseEntity<Object> commentsOn(
            @PathVariable Long post_id, @RequestBody CommentsRequestDto commentsRequestDto,  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(commentsService.saveComment(post_id, commentsRequestDto, userDetails.getUser()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateComments(
            @PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(commentsService.updateComment(id, commentsRequestDto, userDetails.getUser()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComments(
            @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(commentsService.deleteComment(id, userDetails.getUser()));
    }


}
