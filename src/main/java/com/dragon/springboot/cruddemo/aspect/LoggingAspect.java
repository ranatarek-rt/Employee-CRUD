package com.dragon.springboot.cruddemo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.dragon.springboot.cruddemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.dragon.springboot.cruddemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.dragon.springboot.cruddemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
    private void forAllPackages(){}


    //using @before advice

    @Before("forAllPackages()")
    private void printMethod(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toString();
        System.out.println("the method name that is called: " + method);

        //display the arguments passed to functions
        Object [] args = joinPoint.getArgs();
        for(Object arg: args){
            System.out.println("the method arguments: " + arg);
        }

    }

}
