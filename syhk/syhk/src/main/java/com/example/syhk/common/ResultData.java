package com.example.syhk.common;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Data
public class ResultData<T> implements Serializable {
    private int status;
    private Boolean flag;
    private String message;
    private T data;
    private Long timestamp;
    public ResultData(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setFlag(true);
        resultData.setMessage(ReturnCode.RC100.getMessage());
        resultData.setData(data);
        return  resultData;
    }

    public static <T> ResultData<T> fail(Integer code,String message){
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setFlag(false);
        resultData.setMessage(message);
        return resultData;
    }


}



































