package com.example.syhk.Utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 随机打乱传递进来的字符串
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Slf4j
public class UpsetString {

    //    加密盐
    private static final String slat ="syhk#@$&*^";

    public static String upsetString(String str){
        List<String> strings = Arrays.asList((str+slat).split(""));
        Collections.shuffle(strings);
        log.info("通过加密打乱后的 str 为： {}",strings.toString());
        return strings.toString();
    }
}

















































