package com.sparta.springreview.post.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.post.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.sparta.springreview.post.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostRepositoryQueryImpl implements PostRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    // 제목을 키워드로 검색, 수정된 날짜 기준으로 정렬
    @Override
    public List<Post> search(String keyword, Pageable pageable) {
        QPost post = QPost.post;
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.modifiedAt);

        return jpaQueryFactory.select(post)
                .from(post)
                .where(
                        containTitle(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifier)
                .fetch();
    }

    // 키워드 null 체크
    private BooleanExpression containTitle(String keyword) {
        return Objects.isNull(keyword) ? null : post.title.contains(keyword);
    }
}
