package com.example.syhk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO:
 * 配置跨域解决
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/user/**")
               .allowedHeaders("*")
               .allowedMethods("*")
               .maxAge(1800)
               .allowedOrigins("http://localhost:5173");
    }
}
