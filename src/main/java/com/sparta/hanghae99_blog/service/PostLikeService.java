package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.MessageDto;
import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.entity.PostLike;
import com.sparta.hanghae99_blog.entity.User;
import com.sparta.hanghae99_blog.repository.PostLikeRepository;
import com.sparta.hanghae99_blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Transactional
    public MessageDto likeOrDislike(Long id, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));;

        Optional<PostLike> postLike = postLikeRepository.findByUserAndPost(user, post);

        // 중복 좋아요 방지
        if(isNotAlreadyLike(user, post)) {
            postLikeRepository.save(new PostLike(user, post));
            post.updateLikeCount();
            return new MessageDto("좋아요 성공", 200);
        } else {
            postLikeRepository.delete(postLike.get());
            postLikeRepository.flush();
            post.deleteLikeCount();
            return new MessageDto("좋아요 취소", 200);
        }

    }

    // 사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(User user, Post post) {
        return postLikeRepository.findByUserAndPost(user, post).isEmpty();
    }



}
