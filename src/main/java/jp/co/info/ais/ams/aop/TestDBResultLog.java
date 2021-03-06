package jp.co.info.ais.ams.aop;

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
	//DBと繋がると記録に残せるためのメソッド
    @AfterReturning(pointcut = "execution(* jp.co.info.ais.*..*Mapper.*(..))", returning= "result")
    public void DBResultLog(JoinPoint joinPoint, Object result) {
    	if(!(null == result)) {
    		logger.debug(result.toString());
    	}
    }
}
