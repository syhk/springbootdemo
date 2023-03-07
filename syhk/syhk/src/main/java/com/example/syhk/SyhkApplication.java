package com.example.syhk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SyhkApplication {
	public static void main(String[] args) {
		SpringApplication.run(SyhkApplication.class, args);
	}
}
//@ServletComponentScan 在启动类上添加这个注解后，才可以通过注解 @Web... 注解自动注册
