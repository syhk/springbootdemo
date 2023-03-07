package com.example.syhk;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.syhk.Utils.JWTUtils;
import com.example.syhk.entity.Article;
import com.example.syhk.entity.ArticleDetail;
import com.example.syhk.service.ArticleDetailService;
import com.example.syhk.service.ArticleService;
import com.example.syhk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@SpringBootTest
@Slf4j
public class demo1test {

    @Resource
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDetailService articleDetailService;

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
// 测试文章接口
    @Test
    public void blogtest(){
        List<Article> list = articleService.list();
        System.out.println(list.toString());
    }
//     测试文章详细接口 正常
    @Test
    public void articledetail(){
//        List<ArticleDetail> list = articleDetailService.list();
//        log.info("开始打印==================================");
//        System.out.println(list.toString());
//        list.forEach(System.out::println);
//        log.info("id 获取文章详情内容");
//        System.out.println(articleDetailService.getById(2));
//    高度 CRUD 接口
        articleDetailService.save(new ArticleDetail(8,9,"<h1>cpp模板元编程</h1>是非常难的，它是图灵完备的","# cpp模板元编程"));
//        修改
//        articleDetailService.updateById(new ArticleDetail(8,9,"<h2>cpp模板元编程是图灵完备的</h2>","# cpp模板元编程是图灵完备的"));

//        删除
//        articleDetailService.removeById(8);

    }

//    根据用户 id 获取文章
    @Test
    public void articlegetByuserid(){
        log.info("开始获取文章： ");
        List<Article> list = articleService.list(new LambdaQueryWrapper<Article>().eq(Article::getId,2));
        list.forEach(System.out::println);
        Integer[][] a={{1,2,3},{4,5,6,7}};
        System.out.println(a[0].length);
        System.out.println(a[1].length);
    }

}
