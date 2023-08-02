package com.sparta.springreview.aop;

import com.sparta.springreview.comment.entity.Comment;
import com.sparta.springreview.post.entity.Post;
import com.sparta.springreview.security.UserDetailsImpl;
import com.sparta.springreview.user.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionException;

@Aspect
@Component
public class UserCheckAop {

    @Pointcut("execution(* com.sparta.springreview.post.service.PostService.updatePost(..))")
    private void updatePost() {
    }

    @Pointcut("execution(* com.sparta.springreview.post.service.PostService.deletePost(..))")
    private void deletePost() {
    }

    @Pointcut("execution(* com.sparta.springreview.comment.service.CommentService.updateComment(..))")
    private void updateComment() {
    }

    @Pointcut("execution(* com.sparta.springreview.comment.service.CommentService.deleteComment(..))")
    private void deleteComment() {
    }

    @Around("updatePost() || deletePost()")
    public Object executePostUserCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Post post = (Post) joinPoint.getArgs()[0];

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal().getClass() == UserDetailsImpl.class) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();

            if (!post.getUser().equals(user)) {
                throw new RejectedExecutionException("작성자만 게시글을 수정/삭제할 수 있습니다.");
            }
        }
        return joinPoint.proceed();
    }

    @Around("updateComment() || deleteComment()")
    public Object executeCommentUserCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Comment comment = (Comment) joinPoint.getArgs()[0];

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal().getClass() == UserDetailsImpl.class) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();

            if (!comment.getUser().equals(user)) {
                throw new RejectedExecutionException("작성자만 댓글을 수정/삭제할 수 있습니다.");
            }
        }
        return joinPoint.proceed();
    }
}
