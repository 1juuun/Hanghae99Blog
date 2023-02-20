package com.sparta.hanghae99_blog.controller;

import com.sparta.hanghae99_blog.dto.CommentsRequestDto;
import com.sparta.hanghae99_blog.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsApiController {

    private final CommentsService commentsService;

    // 댓글 저장
    @PostMapping("/on/{post_id}")
    public ResponseEntity<Object> commentsOn(@PathVariable Long post_id, @RequestBody CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(commentsService.saveComment(post_id, commentsRequestDto, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateComments(
            @PathVariable Long id, @RequestBody CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(commentsService.updateComment(id, commentsRequestDto, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComments(
            @PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok().body(commentsService.deleteComment(id, request));
    }

}
