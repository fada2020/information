package com.example.demo.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestDBResultLog {

	private static final Logger logger = LogManager.getLogger(TestDBResultLog.class);

    @AfterReturning(pointcut = "execution(* com.example.*..*Mapper.*(..))", returning= "result")
    public void DBResultLog(JoinPoint joinPoint, Object result) {
    	if(!(null == result)) {
    		logger.debug(result.toString());
    	}
    }
}
