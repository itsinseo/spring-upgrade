package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.dto.PostSimpleResponseDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.repository.PostRepository;
import com.sparta.springreview.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final EntityManager entityManager;

    @Override
    public PostListResponseDto getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        return new PostListResponseDto(postList.stream().map(PostSimpleResponseDto::new).toList());
    }

    @Override
    public PostDetailResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto.getTitle(), postRequestDto.getContent(), user);
        postRepository.save(post);
        return new PostDetailResponseDto(post);
    }

    @Override
    public PostDetailResponseDto getOnePost(Long postId) {
        Post post = findPost(postId);
        return new PostDetailResponseDto(post);
    }

    @Override
    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }

    @Override
    @Transactional
    public PostDetailResponseDto updatePost(Post post, PostRequestDto postRequestDto) {
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        entityManager.flush();
        return new PostDetailResponseDto(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
