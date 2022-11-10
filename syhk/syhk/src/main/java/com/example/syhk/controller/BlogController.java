package com.example.syhk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.syhk.common.ResultData;
import com.example.syhk.entity.Article;
import com.example.syhk.entity.ArticleDetail;
import com.example.syhk.service.ArticleDetailService;
import com.example.syhk.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


// 请求方式：应该是先把文章标题，摘要之类的数据加载，如果用户点击哪篇文章来请求对应的详情内容，不请求就不加载

//    测试获取文章列表
    @GetMapping("/getAll")
  public  ResultData<List<Article>> getAll(){
        return ResultData.success(articleService.list());
  }

//  根据用户 id 拿到文章 , 用户个人中文展示的时候，需要用到这个
    @GetMapping("getbyid")
  public ResultData<List<Article>> getbyid(Integer id){
      LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.eq( Article::getId,id);
   return ResultData.success(articleService.list(queryWrapper));
  }



//   首页展示随机获取篇文章
//   测试在线 github vscode 能否编写




//    分页查询
  @GetMapping("/page")
  public ResultData<Page>  getrand(@RequestParam int page ,@RequestParam int pagesize){
       log.info("page = {} , pagesize = {}",page,pagesize);

//       构造分页构造器

      Page pageinfo = new Page(page,pagesize);
//      添加条件查询
// eg : 分页查询时根据更新 or 创建时间进行排序 ， 还可以添加根据名称或主题进行搜索查找

//      执行查询
      articleService.page(pageinfo);
      return ResultData.success(pageinfo);
  }
}




