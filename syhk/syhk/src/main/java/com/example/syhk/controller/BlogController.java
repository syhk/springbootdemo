package com.example.syhk.controller;

import com.example.syhk.common.ResultData;
import com.example.syhk.entity.Article;
import com.example.syhk.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    private static List<Article> list;
    @Autowired
    private ArticleService articleService;



    static {
        list = new ArrayList<>();
        list.add(new Article(1,new Date(),1,"java",10,20,3000,new Date(),"这是关于 java 的相关知识点"));
        list.add(new Article(2,new Date(),2,"cpp",10,20,3000,new Date(),"这是关于 go 的相关知识点"));
        list.add(new Article(4,new Date(),4,"go",10,20,3000,new Date(),"这是关于 cpp 的相关知识点"));
        list.add(new Article(6,new Date(),6,"rust",10,20,3000,new Date(),"这是关于 rust 的相关知识点"));
    }



//    测试获取文章列表
    @GetMapping("/getAll")
public  ResultData<List<Article>> getAll(){

//        List<Article> list = new ArrayList<>();
//        list = articleService.list();
//        for (Article article : list) {
//            System.out.println(article);
//        }
//        return ResultData.success(articleService.list());
        return ResultData.success(articleService.list());
    }







































}
