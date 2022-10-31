package com.example.syhk;

import com.example.syhk.Utils.JWTUtils;
import com.example.syhk.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@SpringBootTest
public class demo1test {

    @Resource
    private UserService userService;


    public static void main(String[] args) {






    }

//    @Test
//    private void test1(){
//        System.out.println(userService.list().toString());
//    }
    @Test
   public void tokentest(){
     String t=  JWTUtils.createToken("123456");
   System.out.println(t);
       System.out.println(JWTUtils.checkToken(t));



   }




   @Test
   public void test3(){
// 测试文件后缀截取是否正确
        String fileName = "syhk.png";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
       System.out.println(suffix);

   }





























}
