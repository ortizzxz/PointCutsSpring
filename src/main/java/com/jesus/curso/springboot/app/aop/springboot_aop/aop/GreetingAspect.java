package com.jesus.curso.springboot.app.aop.springboot_aop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
 
    @AfterReturning("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después de retornar: " + method + ", con los argumentos : " + args);
    }
    
    @AfterThrowing("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.GreetingService.*(..))")
    public void loggerAfterThrow(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después de lanzar la excepción: " + method + ", con los argumentos : " + args);
    }

    @Around("execution(String com.jesus.curso.springboot.app.aop.springboot_aop.services.*.*(..)")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());


        Object result = null;

        try {
            logger.info("El metodo es: " + method + "() con los parametros: " + args);

            result = joinPoint.proceed();

            logger.info("El metodo es: " + method + "() retorna el resultado: " + result);
            return result;
        }catch (Throwable e) {

            logger.error("Error en la llamada del metodo" + method);
            throw e;
        }

}
}
