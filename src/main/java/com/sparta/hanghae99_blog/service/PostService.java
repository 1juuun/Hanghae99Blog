package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.dto.PostResponseDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.entity.User;
import com.sparta.hanghae99_blog.jwt.JwtUtil;
import com.sparta.hanghae99_blog.repository.PostRepository;

import com.sparta.hanghae99_blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 게시물 등록
    @Transactional
    public Post save(PostRequestDto requestDto, HttpServletRequest request) {
        Post post = new Post();

        // jwt에서 token 가져오기!
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 조회 가능
        if(token != null) {
            // 유효한 토큰일 경우 게시글 작성 가능
            if(jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효하지 않은 Token입니다");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            // 게시물 저장하기
            post = postRepository.save(new Post(requestDto, user));

            return post;

        } else {
            throw new IllegalArgumentException("token이 존재하지 않습니다.");
        }
    }

    // 게시물 전체 리스트 조회
    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 해당 게시물 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long id) {

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostResponseDto(post);
    }

    // 해당 게시물 수정
    @Transactional
    public ResponseEntity<Object> update(
            Long id, PostRequestDto requestDto, HttpServletRequest request) {

        // jwt에서 token 가져오기!
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 조회 가능
        if (token != null) {

            // 유효한 토큰일 경우 게시글 작성 가능
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효하지 않은 Token입니다");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
            Optional<User> user = userRepository.findByUsername(claims.getSubject());
            if(user.isEmpty()) {
                throw new IllegalArgumentException("해당 사용자가 없습니다.");
            }

            // 해당하는 게시글이 DB에 있는지 확인
            Optional<Post> post = postRepository.findById(id);
            if (post.isEmpty()) {
                throw new IllegalArgumentException("해당하는 글이 존재하지 않습니다");
            }

            // 선택한 게시글의 작성자와 토큰에서 가져온 사용자 정보가 일치하는지 확인
            post = postRepository.findByIdAndUserId(id, user.get().getId());
            if (post.isEmpty()) { // 일치하는 게시물이 없다면
                throw new IllegalArgumentException("수정할 권한이 없습니다.");
            }

            // 게시물 수정하기
            post.get().update(requestDto, user.get());
            return ResponseEntity.ok()
                    .body(new PostResponseDto(post.get()));
        } else {
            throw new IllegalArgumentException("token이 존재하지 않습니다.");
        }
    }

    // 해당 게시물 삭제
    public Post deletePost(Long id, HttpServletRequest request) {

        Post post = new Post();

        // jwt에서 token 가져오기!
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 조회 가능
        if (token != null) {

            // 유효한 토큰일 경우 게시글 작성 가능
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("유효하지 않은 Token입니다");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB조회
            Optional<User> user = userRepository.findByUsername(claims.getSubject());
            if(user.isEmpty()) {
                throw new IllegalArgumentException("해당 사용자가 없습니다.");
            }

            // 해당하는 게시글이 DB에 있는지 확인
            Optional<Post> postOptional = postRepository.findById(id);
            if (postOptional.isEmpty()) {
                throw new IllegalArgumentException("해당하는 글이 존재하지 않습니다");
            }

            // 선택한 게시글의 작성자와 토큰에서 가져온 사용자 정보가 일치하는지 확인
            postOptional = postRepository.findByIdAndUserId(id, user.get().getId());
            if (postOptional.isEmpty()) { // 일치하는 게시물이 없다면
                throw new IllegalArgumentException("삭제할 권한이 없습니다.");
            }

            // 게시물 삭제하기
            postRepository.deleteById(id);
            return post;
        } else {
            throw new IllegalArgumentException("token이 존재하지 않습니다.");
        }
    }

}
