package com.zongs365.service.swagger.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问资源定义： /** 所有资源
        registry.addMapping("/**")
                .allowedOrigins("*")
                // 允许发送Cookie
                .allowCredentials(true)
                // 允许 GET", POST, PUT, DELETE 方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
