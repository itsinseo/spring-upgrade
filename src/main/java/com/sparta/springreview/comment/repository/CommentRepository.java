package com.sparta.springreview.comment.repository;

import com.sparta.springreview.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
