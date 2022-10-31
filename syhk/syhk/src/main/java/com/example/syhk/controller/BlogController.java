package com.example.syhk.controller;

import com.example.syhk.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@RestController
@Slf4j
@RequestMapping("/blog")
public class BlogController {
    //    造一个文章表
   private static Map<String,String> map = new HashMap<>();
    static {
     map.put("syhk1", "javajava的JVM");
        map.put("syhk2", "cppcpp复杂");
        map.put("syhk3","rustrust很难");
        map.put("syhk4", "go优雅简洁");
}




//    测试获取文章列表
    @GetMapping("/getAll")
public  ResultData<Map<String,String>> getAll(){
        return  ResultData.success(map);
    }

















































}
