package com.jesus.curso.springboot.app.aop.springboot_aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class GreetingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Before("execution(String com.jesus.curso.springboot.app.aop.springboot_aop...*.*(..))")   
    //@Before("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.*.*(..))")   
    //@Before("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")   
    @Before("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))") // (..) -> expresion regular
    public void loggerBefore(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: " + method + ", con los argumentos : " + args);
    }
 
    @After("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.GreetingService.sayHello(..))")
    public void loggerAfter(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después: " + method + ", con los argumentos : " + args);
    }
}
