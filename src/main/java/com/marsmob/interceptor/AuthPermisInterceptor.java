package com.marsmob.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.marsmob.annotation.Permissions;

/**
 * 权限拦截器
 * @describe 
 * @author wcc
 * @data 2018年1月8日
 * @version v1.0
 * @copyright LH.GROUP
 */
public class AuthPermisInterceptor  extends HandlerInterceptorAdapter{
	
	// 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod,这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method  method  =  handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Permissions permissions = method.getAnnotation(Permissions.class);
        if (permissions == null) {
        // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (StringUtils.isNotBlank(permissions.value()) ) {
            // 如果权限配置不为空, 则取出配置值
            String permissionsUrl =permissions.value();
             
            //TODO session中取到权限集合list 然后对比 permissionsUrl 是否包含在内。
             
        }
        return true;
    }

}
