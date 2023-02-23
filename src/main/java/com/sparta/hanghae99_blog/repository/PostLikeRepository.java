package com.sparta.hanghae99_blog.repository;

import com.sparta.hanghae99_blog.entity.Post;
import com.sparta.hanghae99_blog.entity.PostLike;
import com.sparta.hanghae99_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post);

    Optional<PostLike> findByPostId(Long id);
}
