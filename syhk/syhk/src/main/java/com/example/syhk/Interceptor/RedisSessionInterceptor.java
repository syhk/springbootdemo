package com.example.syhk.Interceptor;

import com.example.syhk.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Component
@Slf4j
public class RedisSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入了拦截器");
        HttpSession session = request.getSession();
        //        redis 里面的 session 判断用户是否已经登录
        String userid=redisTemplate.opsForValue().get("userid");
        String sessionId = (String) redisTemplate.opsForValue().get("loginUser:" + userid);
        log.info("拦截器中 redis 中拿到的数据为： " + sessionId + "     " + "从 redis 中的拿到的用户 id 为 ：" + userid);
        log.info("当前的 session id 为： "+session.getId());
        if (sessionId != null && userid != null ) {
            log.info("accept login");
//            用户已经登录
            return true;
        }
        log.info("notlogin");
        return false;
    }

}



































