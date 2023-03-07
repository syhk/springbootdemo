package com.example.syhk.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
// 可以实现三个功能： 全局异常处理 全局数据绑定  全局数据处理
@RestControllerAdvice // 作用： 拦截Controller 方法的返回值，统一处理返回值/响应体
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

//    是否支持 advice 功能， 默认为 false
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

//    对返回的数据进行处理
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("进行了 beforeBodyWrite...................");
        if(body instanceof String)
        {
            return objectMapper.writeValueAsString(ResultData.success(body));
        }

        if(body instanceof ResultData<?>){
            return body;
        }

        return ResultData.success(body);
    }
}