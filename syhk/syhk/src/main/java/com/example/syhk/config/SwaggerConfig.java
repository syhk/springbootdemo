package com.example.syhk.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置类
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
// 出现问题暂时不使用
//@Configuration
//@EnableSwagger2 // 需要显式开启 Swagger2
public class SwaggerConfig {
//   @Bean
   public Docket createRestApi() {
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(PathSelectors.any())
               .build();
   }

   private ApiInfo apiInfo(){
       return new ApiInfoBuilder()
               .title("demo-vue")
               .description("demo-vue的接口文档")
               .termsOfServiceUrl("https://www.bilibili.com/")
               .version("1.0")
               .build();
   }

}












