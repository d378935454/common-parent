package com.bean.aspect;

import com.bean.token.TokenUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.MyLogger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ppctest02 on 2017/2/10.
 */
@Component
@Aspect
public class TokenAspect {

    private final MyLogger log = new MyLogger(TokenAspect.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("@annotation(com.bean.token.Token)")
    public void token() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     */
    @Before("token()")
    public void before(JoinPoint joinPoint) {

    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("token()")
    public void after(JoinPoint joinPoint) {
//        log.info("after " + joinPoint);
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("token()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 检查用户所传递的 token 是否合法
            String token = request.getHeader("auth_token");
            if (TokenUtil.isValid(token,"aaaaa")) {
                return "错误, 权限不合法!";
            }
            Object retVal=joinPoint.proceed();
            //继续执行方法
            stopWatch.stop();
            reportToMonitorSystem(joinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis());
            return  retVal;

        } catch (Throwable e) {

            long end = System.currentTimeMillis();
            log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            return "";
        }
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("token()")
    public void afterReturn(JoinPoint joinPoint) {
//        log.info("afterReturn " + joinPoint);
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "token()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
    }

    public void reportToMonitorSystem(String methodName, long expiredTime) {
        log.info("---method {0} invoked, expired time: {1} ms---", methodName, expiredTime);
    }
}
