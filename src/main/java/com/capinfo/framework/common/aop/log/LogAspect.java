package com.capinfo.framework.common.aop.log;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LogAspect implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {



	// service方法执行之前被调用
    public void before(Method method, Object[] args, Object target) throws Throwable {  

    }  
  
    // service方法执行完之后被调用  
    public void afterReturning(Object arg0, Method method, Object[] args, Object target) throws Throwable {  
    }  
  
    // 抛出Exception之后被调用 
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {  
         
    }  
  
}
