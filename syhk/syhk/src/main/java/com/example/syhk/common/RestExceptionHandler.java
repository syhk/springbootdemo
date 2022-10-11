package com.example.syhk.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * TODO:
 *  全局异常处理器
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({Exception.class}) //统一处理某一类异常，就是想要处理的异常
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //指定客户端收到的 http 状态码
    public ResultData<String> exception(Exception e){
        log.error("全局异常信息 e={}",e.getMessage(),e);
        return ResultData.fail(ReturnCode.RC500.getCode(), e.getMessage());
    }


}
