package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.CommentsRequestDto;
import com.sparta.hanghae99_blog.dto.CommentsResponseDto;
import com.sparta.hanghae99_blog.dto.MessageDto;
import com.sparta.hanghae99_blog.entity.Comments;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.entity.User;
import com.sparta.hanghae99_blog.entity.UserRoleEnum;
import com.sparta.hanghae99_blog.jwt.JwtUtil;
import com.sparta.hanghae99_blog.repository.CommentsRepository;
import com.sparta.hanghae99_blog.repository.PostRepository;
import com.sparta.hanghae99_blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final JwtUtil jwtUtil;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 댓글 저장
    @Transactional
    public CommentsResponseDto saveComment(Long post_id, CommentsRequestDto commentsRequestDto, HttpServletRequest request) {
        // jwt에서 token 가져오기!
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 댓글 조회 가능
        if (token == null) {
            throw new IllegalArgumentException("token이 존재하지 않습니다.");
        }
        // 유효한 토큰일 경우 댓글 작성 가능
        if (jwtUtil.validateToken(token)) {
            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            throw new IllegalArgumentException("유효하지 않은 Token입니다");
        }
        // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        // 댓글 쓰기 전 해당하는 게시글 존재여부 조회
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + post_id)
        );

        // 댓글 저장하기
        Comments comments = commentsRepository.saveAndFlush(new Comments(commentsRequestDto, post, user));
        return new CommentsResponseDto(comments);
    }

    @Transactional
    public CommentsResponseDto updateComment(
            Long id, CommentsRequestDto commentsRequestDto, HttpServletRequest request) {

        // jwt에서 token 가져오기!
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 조회 가능
        if (token == null) {
            throw new IllegalArgumentException("token이 존재하지 않습니다.");
        }
        // 유효한 토큰일 경우 게시글 작성 가능
        if (jwtUtil.validateToken(token)) {
            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            throw new IllegalArgumentException("유효하지 않은 Token입니다");
        }
        // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(IllegalArgumentException::new);

        // 해당 게시물의 댓글이 DB에 있는지 확인
        Comments comments = commentsRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // 사용자 권한 가져오기
        UserRoleEnum role = user.getRole();

        if(role == UserRoleEnum.ADMIN || comments.getUser().equals(user)) {
            // 댓글 수정하기
            comments.update(id, commentsRequestDto, user);
        } else {
            throw new IllegalArgumentException("삭제할 권한이 없습니다.");
        }

        return new CommentsResponseDto(comments);

    }

    @Transactional
    public MessageDto deleteComment(Long id, HttpServletRequest request) {
        // jwt에서 token 가져오기!
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 조회 가능
        if (token == null) {
            throw new IllegalArgumentException("token이 존재하지 않습니다.");
        }
        // 유효한 토큰일 경우 게시글 작성 가능
        if (jwtUtil.validateToken(token)) {
            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            throw new IllegalArgumentException("유효하지 않은 Token입니다");
        }
        // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(IllegalArgumentException::new);
        // 해당하는 댓글이 DB에 있는지 확인
        Comments comments = commentsRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // 사용자 권한 가져오기
        UserRoleEnum role = user.getRole();

        if(role == UserRoleEnum.ADMIN || comments.getUser().equals(user)) {
            // 댓글 삭제하기
            commentsRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("삭제할 권한이 없습니다.");
        }

        return new MessageDto("success", 200);
    }

}
