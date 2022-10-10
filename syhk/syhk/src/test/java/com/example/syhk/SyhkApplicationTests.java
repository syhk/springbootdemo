package com.example.syhk;

import com.example.syhk.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class SyhkApplicationTests {

@Resource
private UserService userService;

@Test
private  void text1() {
    System.out.println(userService.list().toString());

}

}



































