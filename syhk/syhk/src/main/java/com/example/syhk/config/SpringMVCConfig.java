package com.example.syhk.config;

import com.example.syhk.filters.FilterConfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  解决前端后端分离 sessionID 不一致问题：
 *  引入了这个类和  FilterConfig 类来解决
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@SuppressWarnings("deprecation")
@SpringBootConfiguration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Autowired
    private FilterConfg filterConfg;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filterConfg).addPathPatterns("/**");
    }
}
