package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostResponseDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        return postList.stream().map(PostResponseDto::new).toList();
    }
}
