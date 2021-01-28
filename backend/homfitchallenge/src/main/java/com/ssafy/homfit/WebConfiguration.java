package com.ssafy.homfit;

import com.ssafy.homfit.interceptor.JwtInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
    private static final String[] EXCLUED_PATHS = { "/","/user/login","/user/signup", "/error/**"};

    @Autowired
    private JwtInterceptor jwtInterceptor;

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(jwtInterceptor)
    //     .addPathPatterns("/**")
    //     .excludePathPatterns(EXCLUED_PATHS);
    // }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
    }

}
