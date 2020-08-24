package com.sy.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1.注册Springmvc的拦截器
 * 2.添加拦截器的拦截路径
 */
@Configuration
public class InterceptorRules implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("==============================");
        //注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new CORSInterceptor());
        //拦截路径
        interceptorRegistration.addPathPatterns("/**");
    }
}
