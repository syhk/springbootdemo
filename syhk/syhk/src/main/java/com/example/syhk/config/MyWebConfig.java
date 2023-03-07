package com.example.syhk.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;
/**
 * TODO:
 * 配置跨域解决
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
               .allowedHeaders("*")
               .allowedMethods("*")
               .allowCredentials(true)
               .allowedOrigins("*")
               .maxAge(1800)
               .allowedOrigins("http://localhost:5173");
    }
}



























