package com.marriage.config;


import com.marriage.model.ResponseMsg;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.marriage.utils.AccessHelper.getIpAddress;
import static com.marriage.utils.ResponseMsgUtil.getFailMsg;


/**
 * @author hu
 */
@Aspect
@Configuration
@Slf4j
public class LogRecordAspect {    

    /**
     * 定义一个切入点.
     */
    @Pointcut("execution(* com.marriage.*.controller..*Controller.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        ResponseMsg<?> result;
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 记录下请求内容
            log.info("REQUEST URL : " + request.getRequestURL().toString());
            log.info("REQUEST HTTP METHOD : " + request.getMethod());
            log.info("REQUEST HTTP PARAM : " + parameterMapLog(request.getParameterMap()));
            log.info("REQUEST IP : " + getIpAddress(request));
            log.info("REQUEST CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            log.info("REQUEST ARGS : " + Arrays.toString(pjp.getArgs()));

            result = (ResponseMsg<?>) pjp.proceed();
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        // 处理完请求，返回内容
        log.info("RESPONSE head: " + result.getHead());
        log.info("RESPONSE data : " + result.getData());
        log.info("RESPONSE : " + pjp.getSignature() + " SPEND TIME : " + (System.currentTimeMillis() - startTime));
        return result;
    }

    private ResponseMsg<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResponseMsg<?> result = getFailMsg(null,"系统异常，请联系管理员",null);
        log.error(pjp.getSignature() + " error ", e);
        return result;
    }
    private Map<String,String> parameterMapLog(Map<String,String[]> map){
        Map<String,String> result=new LinkedHashMap<>();
        if(map==null||map.size()<=0){
            return result;
        }
        for(Map.Entry<String,String[]> entry:map.entrySet()){
            result.put(entry.getKey(),Arrays.toString(entry.getValue()));
        }
        return result;
    }
}
