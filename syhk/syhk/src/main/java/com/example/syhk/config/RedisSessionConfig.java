package com.example.syhk.config;

import com.example.syhk.Interceptor.RedisSessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) // session 过期时间
@Configuration
@SpringBootConfiguration
public class RedisSessionConfig implements WebMvcConfigurer {
    @Autowired
    RedisSessionInterceptor redisSessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        允许登录和注册，接口
        // 添加拦截器并设置排除路径
        registry.addInterceptor(redisSessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/loginredis")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/downheadpor");
    }
}
