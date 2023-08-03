package com.sparta.springreview.aop;

import com.sparta.springreview.exception.PatternMismatchException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Aspect
@Component
public class SignupPatternCheckAop {
    @Pointcut("execution(* com.sparta.springreview.user.controller.UserController.signup(..))")
    public void signup() {
    }

    // 회원가입 닉네임, 비밀번호 패턴 예외처리
    @Around("signup()")
    public Object executeSignupPatternCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        BindingResult bindingResult = (BindingResult) joinPoint.getArgs()[1];

        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage());
            }
            String patternErrorMessage = stringBuilder.toString();
            throw new PatternMismatchException(patternErrorMessage);
        }
        return joinPoint.proceed();
    }
}
