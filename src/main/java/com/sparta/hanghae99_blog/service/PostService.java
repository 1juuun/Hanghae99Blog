package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.dto.PostResponseDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post save(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        postRepository.save(post);

        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifedAtDesc();
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

    public boolean deletePost(Long id) {

        postRepository.deleteById(id);

        return true;
    }
}
