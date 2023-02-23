package com.sparta.hanghae99_blog.repository;

import com.sparta.hanghae99_blog.entity.Comments;
import com.sparta.hanghae99_blog.entity.CommentsLike;
import com.sparta.hanghae99_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsLikeRepository extends JpaRepository<CommentsLike, Long> {
    Optional<CommentsLike> findByUserAndComments(User user, Comments comments);
}
