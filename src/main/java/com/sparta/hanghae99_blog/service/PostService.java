package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.MessageDto;
import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.dto.PostResponseDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.entity.User;
import com.sparta.hanghae99_blog.entity.UserRoleEnum;
import com.sparta.hanghae99_blog.jwt.JwtUtil;
import com.sparta.hanghae99_blog.repository.PostRepository;

import com.sparta.hanghae99_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 게시물 등록
    @Transactional
    public PostResponseDto save(PostRequestDto requestDto, User user) {
        // 게시물 저장하기
        Post post = postRepository.save(new Post(requestDto, user));
        return new PostResponseDto(post);
    }

    // 게시물 전체 리스트 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {

        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }

        return postResponseDtoList;
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
    public PostResponseDto update(
            Long id, PostRequestDto requestDto, User user) {
        // 해당하는 게시글이 DB에 있는지 확인
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // 사용자 권한 가져와서 ADMIN 이면 무조건 수정 가능, USER 면 본인이 작성한 글일 때만 수정 가능
        UserRoleEnum role = user.getRole();

        if(role == UserRoleEnum.ADMIN || post.getUser().getUsername().equals(user.getUsername())) {
            // 게시물 수정하기
            post.update(requestDto, user);
        } else {
            throw new IllegalArgumentException("수정할 권한이 없습니다.");
        }

        return new PostResponseDto(post);

    }

    // 해당 게시물 삭제
    public MessageDto deletePost(Long id, User user) {

        // 해당하는 게시글이 DB에 있는지 확인
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // 사용자 권한 가져오기
        UserRoleEnum role = user.getRole();

        if(role == UserRoleEnum.ADMIN || post.getUser().getUsername().equals(user.getUsername())) {
            // 게시물 삭제하기
            postRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("삭제할 권한이 없습니다.");
        }

        return new MessageDto("게시글 삭제 성공", 200);
    }

}


