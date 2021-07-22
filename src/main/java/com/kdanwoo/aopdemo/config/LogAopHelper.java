package com.kdanwoo.aopdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAopHelper {
    /**
     * @Pointcut : aspectJ를 적용할 타겟을 정의해준다. 전체 컨트롤러의 함수대상, 특정 어노테이션을 설정한 함수대상, 특정 메소드 대상 등 개발자가 적용하길 원하는 범위를 정의하는 어노테이션
     * @Before : aspectJ를 적용할 타겟 메소드가 실행되기 '전' 수행됨
     * @AfterReturning : aspectJ를 적용할 타겟 메소드가 실행된 '후' 수행됨 (제일 마지막에 수행됨)
     * @Around : aspectJ를 적용할 타겟 메소드 실행 전 , 후 처리를 모두 할 수 있음
     * */


    /**
     *   @GetMapping 설정된 메소드 또는 클래스 설정
     *   GetMapping 노테이션이 설정된 특정 클래스/메소드에만 AspectJ가 적용됨.
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void GetMapping(){ }

    /**
     * @param joinPoint
     */
    @Before("GetMapping()")
    public void before(JoinPoint joinPoint) {
        log.info("=====================AspectJ TEST  : Before Logging Start=====================");
        log.info("=====================AspectJ TEST  : Before Logging End=====================");
    }

    /**
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "GetMapping()", returning = "result")
    public void AfterReturning(JoinPoint joinPoint, Object result) {
        log.info("=====================AspectJ TEST  : AfterReturning Logging Start=====================");
        log.info("=====================AspectJ TEST  : AfterReturning Logging END=====================");
    }

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("GetMapping()")
    public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=====================AspectJ TEST  : Around Logging Start=====================");
        try {
            Object result = joinPoint.proceed();
            log.info("=====================AspectJ TEST  : Around Logging END=====================");
            return result;
        }catch (Exception e) {
            log.error("=====================AspectJ Around Exception=====================");
            log.error(e.toString());
            return null;
        }
    }

}
