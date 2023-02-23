package com.sparta.hanghae99_blog.repository;

import com.sparta.hanghae99_blog.entity.Comments;
import com.sparta.hanghae99_blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findAllByPost(Post post);

}
