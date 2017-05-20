package com.bean.springboot.aspect;

import com.bean.springboot.token.Token;
import com.bean.springboot.token.TokenUtil;
import com.bean.springboot.utils.RSTFulBody;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * Created by ppctest02 on 2017/2/10.
 */
@Component
@Aspect
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenAspect implements Ordered {

    private static final Logger log = LoggerFactory.getLogger(TokenAspect.class);
    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("@annotation(com.bean.springboot.token.Token)")
    public void token() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
     * 同时接受JoinPoint切入点对象,可以没有该参数
     */
    @Before("token()")
    public void before(JoinPoint joinPoint) {
       Class aClass=joinPoint.getTarget().getClass();
        MethodSignature methodSignature=(MethodSignature)joinPoint.getSignature();
        methodSignature.getMethod().getAnnotation(com.bean.springboot.token.Token.class);

    }

    //配置后置通知,使用在方法aspect()上注册的切入点
    @After("token()")
    public void after(JoinPoint joinPoint) {
//        log.info("after " + joinPoint);
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("token()&&@annotation(token1)")
    public Object around(ProceedingJoinPoint joinPoint, Token token1) {
        long start = System.currentTimeMillis();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 检查用户所传递的 token 是否合法
            String token = request.getHeader("auth_token");
            if (!TokenUtil.isValid(token,TokenUtil.getSECRET())) {
                return new RSTFulBody().fail().data("secret错误！");
            }
            if(TokenUtil.isTimeOut(token,TokenUtil.getSECRET())){
                return new RSTFulBody().fail().data("验证已过期");
            }
            Object retVal=joinPoint.proceed();
            //继续执行方法
            stopWatch.stop();
            reportToMonitorSystem(joinPoint.getSignature().toShortString(), stopWatch.getTotalTimeMillis());
            return  retVal;

        } catch (Throwable e) {

            long end = System.currentTimeMillis();
            log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            return "系统错误";
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


    @Override
    public int getOrder() {
        return 0;
    }
}
