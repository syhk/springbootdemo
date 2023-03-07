package com.example.syhk.common;

import lombok.Data;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */

public enum ReturnCode {

    RC100(100,"操作成功"),
    RC999(999,"操作失败"),
    RC200(200,"请稍后再试"),
    RC500(500,"系统异常，请稍后再试");

    private final Integer code;

    private final String message;

    ReturnCode(Integer code , String message){
        this.code=code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }


}
