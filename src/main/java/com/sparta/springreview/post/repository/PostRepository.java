package com.sparta.springreview.post.repository;

import com.sparta.springreview.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryQuery {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
