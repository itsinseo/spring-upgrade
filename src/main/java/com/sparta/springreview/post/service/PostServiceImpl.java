package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.dto.PostResponseDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.repository.PostRepository;
import com.sparta.springreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        return postList.stream().map(PostResponseDto::new).toList();
    }

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent(), user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }
}
