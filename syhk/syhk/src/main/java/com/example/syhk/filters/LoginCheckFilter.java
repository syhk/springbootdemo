package com.example.syhk.filters;

import com.alibaba.fastjson.JSON;
import com.example.syhk.common.ResultData;
import com.example.syhk.common.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Slf4j
@WebFilter("/*")
public class LoginCheckFilter implements Filter {

//   路径匹配器，支持通配符
    private static final AntPathMatcher PATH_MATCHER= new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("进入了拦截器");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =(HttpServletResponse) response;

//        获取本次请求的 URL
        String url = httpServletRequest.getRequestURI();
        log.info("请求的URL 为（拦截到的请求为：）： {}",url);

//        需要直接放行的请求路径
        String[] urls = new String[]{
          "/user/login"
        };

//        判断请求是否需要处理
   boolean check=     check(urls,url);

//   不需要处理直接放行
        if(check){
            log.info("本次请求不需要处理： {}",url);
            chain.doFilter(request,response);
            return;
        }

//        判断登录状态
        if(httpServletRequest.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户 id 为： {}",httpServletRequest.getSession().getAttribute("user"));
            chain.doFilter(request,response);
            return;
        }

        log.info("用户未登录");
//        未登录
//        TODO 这里中文前端接收乱码，暂时未解决
//        暂时只能单独设置解决，全局配置没有解决
        httpServletResponse.setCharacterEncoding("GBK");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultData.fail(ReturnCode.RC999.getCode(), "用户未登录")));

// 在前端拦截器中处理重定向或转发到哪里（登录页）

    }



//    路径匹配
    public boolean check(String[] urls , String url){
        for (String s : urls) {

        boolean math=    PATH_MATCHER.match(s,url);
        if(math){
            return true;
        }
        }
        return false;
    }


}



































