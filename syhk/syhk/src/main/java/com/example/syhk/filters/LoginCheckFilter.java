package com.example.syhk.filters;
import com.alibaba.fastjson.JSON;
import com.example.syhk.common.ResultData;
import com.example.syhk.common.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * TODO: 可以利用 redis 解决 session 共享问题，大量被访问的数据，利用缓存优化提高速度
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
//   路径匹配器，支持通配符
    private static final AntPathMatcher PATH_MATCHER= new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("进入了过滤器");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse =(HttpServletResponse) response;
//        获取本次请求的 URL
        String url = httpServletRequest.getRequestURI();
        log.info("请求的URL 为（拦截到的请求为：）： {}",url);
//        携带的 token 为
        String token = httpServletRequest.getHeader("携带的 token 为： "+"token");

//        需要直接放行的请求路径
        String[] urls = new String[]{
          "/user/login",
                "/user/loginredis",
                "/user/register",
//                "/blog/page",
                //暂时先放开 getallblog() 方法使用
//                "/blog/getAll",
        };
//        判断请求是否需要处理
   boolean check= check(urls,url);
//   不需要处理直接放行
        if(check){
            log.info("本次请求不需要处理： {}",url);
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        HttpSession session = httpServletRequest.getSession();



        log.info("本次请求的 sessionid 为 ： "+session.getId());
//        判断登录状态
        log.info("filter ： "+httpServletRequest.getSession().getAttribute("user"));
        log.info(session.getId()+"======"+session.getAttribute("user"));
//        Sessionid 不一致问题暂未解决
        if(session.getAttribute("user") != null){
            log.info("用户已登录，用户 id 为： {}",session.getAttribute("user"));
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        log.info("用户未登录");
//        未登录
//        TODO 这里中文前端接收乱码，暂时未解决
//        暂时只能单独设置解决，全局配置没有解决
        httpServletResponse.setCharacterEncoding("GBK");
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultData.fail(ReturnCode.RC999.getCode(), "用户未登录")));
        httpServletResponse.getWriter().close();
// 在前端拦截器中处理重定向或转发到哪里（登录页）
       }
//    路径匹配
    public boolean check(String[] urls , String url){
        for (String s : urls) {
        boolean math=    PATH_MATCHER.match(s,url);
        if(math){
            return true;
        }}
        return false;
    }
}