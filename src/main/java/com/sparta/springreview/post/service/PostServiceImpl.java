package com.sparta.springreview.post.service;

import com.sparta.springreview.post.dto.PostDetailResponseDto;
import com.sparta.springreview.post.dto.PostListResponseDto;
import com.sparta.springreview.post.dto.PostRequestDto;
import com.sparta.springreview.post.dto.PostSimpleResponseDto;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.repository.PostRepository;
import com.sparta.springreview.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public PostListResponseDto getAllPosts(Pageable pageable) {
        Page<Post> postList = postRepository.findAllByOrderByCreatedAtDesc(pageable);
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
    @Transactional
    public PostDetailResponseDto updatePost(Post post, PostRequestDto postRequestDto) {
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        return new PostDetailResponseDto(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public PostListResponseDto searchPost(String keyword, Pageable pageable) {
        return new PostListResponseDto(
                postRepository.search(keyword, pageable)
                        .stream().map(PostSimpleResponseDto::new)
                        .toList()
        );
    }

    @Override
    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.")
        );
    }
}
