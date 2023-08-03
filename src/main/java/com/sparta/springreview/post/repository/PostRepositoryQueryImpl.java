package com.sparta.springreview.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.springreview.post.entity.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import static com.sparta.springreview.post.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryQueryImpl implements PostRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> search(String keyword) {
        return jpaQueryFactory.select(post)
                .from(post)
                .where(
                        containTitle(keyword)
                )
                .fetch();
    }

    // 제목을 키워드로 검색
    private BooleanExpression containTitle(String keyword) {
        return Objects.nonNull(keyword) ? post.title.contains(keyword) : null;
    }
}
