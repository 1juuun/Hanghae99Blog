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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        }

        return post;

    }

    // 게시물 조회
    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long id) {

        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostResponseDto(post);
    }

    @Transactional
    public boolean update(Long id, PostRequestDto requestDto) {

       Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 글이 존재하지 않습니다")
       );

       post.update(requestDto);

       return true;
    }

    @Transactional
    public boolean deletePost(Long id) {

        postRepository.deleteById(id);

        return true;
    }
}
