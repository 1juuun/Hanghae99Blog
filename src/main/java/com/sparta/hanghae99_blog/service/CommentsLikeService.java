package com.sparta.hanghae99_blog.service;

import com.sparta.hanghae99_blog.dto.MessageDto;
import com.sparta.hanghae99_blog.entity.*;
import com.sparta.hanghae99_blog.repository.CommentsLikeRepository;
import com.sparta.hanghae99_blog.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsLikeService {

    private final CommentsRepository commentsRepository;
    private final CommentsLikeRepository likeRepository;


    @Transactional
    public MessageDto likeOrDislike(Long id, User user) {
        Comments comments = commentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));

        Optional<CommentsLike> commentsLike = likeRepository.findByUserAndComments(user, comments);

        if(isNotAlreadyLike(user, comments)) {
            likeRepository.save(new CommentsLike(user, comments));
//            postResponseDto.addPostLike(post);
            return new MessageDto("좋아요 성공", 200);
        } else {
            likeRepository.delete(commentsLike.get());
            likeRepository.flush();
//            postResponseDto.minusPostLike();
            return new MessageDto("좋아요 취소", 200);
        }

    }

    private boolean isNotAlreadyLike(User user, Comments comments) {
        return likeRepository.findByUserAndComments(user, comments).isEmpty();
    }

}
