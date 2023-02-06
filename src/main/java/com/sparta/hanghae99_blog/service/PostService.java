package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.PostRequestDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

}
