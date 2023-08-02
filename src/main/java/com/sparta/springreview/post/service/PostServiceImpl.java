package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.dto.PostSimpleResponseDto;
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
    public PostListResponseDto getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        return new PostListResponseDto(postList.stream().map(PostSimpleResponseDto::new).toList());
    }

    @Override
    public PostSimpleResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent(), user);
        postRepository.save(post);
        return new PostSimpleResponseDto(post);
    }

    @Override
    public PostDetailResponseDto getOnePost(Long postId) {
        Post post = findPost(postId);
        return new PostDetailResponseDto(post);
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }
}
