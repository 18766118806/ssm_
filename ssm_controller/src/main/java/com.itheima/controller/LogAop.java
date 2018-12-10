package com.itheima.controller;


import com.itheima.domain.SysLog;
import com.itheima.service.impl.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/8 18:34
 **/

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date visitTime; //访问的时间
    private Class aClass; //访问的类
    private Method method; //访问的方法pr
    private String url;  //访问的url
    private String username;//访问的用户
    private String ip;  //访问者的ip


    @Around("execution(* com.itheima.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        Object proceed =null;  //方法返回值
        visitTime = new Date ();
        Object target = pj.getTarget ();
        aClass = target.getClass ();
        String methodName = pj.getSignature ().getName ();
        Object[] args = pj.getArgs ();

        if (args == null || args.length == 0) {
            method = aClass.getMethod (methodName);  //无参
        } else {
            Class[] argsClass = new Class[args.length]; //有参
            for (int i = 0; i < args.length; i++) {
                argsClass[i] = args[i].getClass ();
            }
            method = aClass.getMethod (methodName, argsClass);
        }
        //获取url
        if (aClass != null && method != null && aClass != LogAop.class) {
            String valueClass = null;   //类RequestMapping value值
            String valueMethod = null;  //方法RequestMapping value值
            RequestMapping annotation = (RequestMapping) aClass.getAnnotation (RequestMapping.class);
            if (annotation != null) {
                valueClass = annotation.value ()[0];
            }
            if (method != null) {
                RequestMapping requestMapping = method.getAnnotation (RequestMapping.class);
                if (requestMapping != null) {
                    valueMethod = requestMapping.value ()[0];
                }
            }
            url = valueClass + valueMethod;

            //获取ip
            ip = request.getRemoteAddr ();

            //获取访问用户
            SecurityContext context = SecurityContextHolder.getContext ();
            User principal = (User) context.getAuthentication ().getPrincipal ();
            username = principal.getUsername ();

            Date endTime = null;   //结束时间
            if (method != null) {
                try {
                   //method.invoke (aClass.newInstance (), args);
                  proceed  = pj.proceed (args);
                } catch (InvocationTargetException e) {
                    e.printStackTrace ();
                }
                endTime = new Date ();
            }

            //将日志信息封装到SysLog
            SysLog sysLog = new SysLog ();
            sysLog.setExecutionTime (endTime.getTime () - visitTime.getTime ());
            sysLog.setIp (ip);
            sysLog.setMethod ("访问的方法:" + aClass.getName () + methodName);
            sysLog.setUrl (url);
            sysLog.setUsername (username);
            sysLog.setVisitTime (visitTime);
            //调用业务层将日志插入到数据库
            sysLogService.save (sysLog);
        }
        return proceed;
    }
}
