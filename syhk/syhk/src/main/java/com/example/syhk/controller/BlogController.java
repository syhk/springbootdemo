package com.example.syhk.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.syhk.common.ResultData;
import com.example.syhk.entity.Article;
import com.example.syhk.entity.User;
import com.example.syhk.entity.request.Articlereq;
import com.example.syhk.entity.request.ReturnArticle;
import com.example.syhk.service.ArticleService;
import com.example.syhk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 * TODO:
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@RestController
@Slf4j
@RequestMapping("/blog")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")
@Api(tags = "博客数据接口")
public class BlogController {
    private static List<Article> list;
    @Autowired
    private ArticleService articleService;
// 请求方式：应该是先把文章标题，摘要之类的数据加载，如果用户点击哪篇文章来请求对应的详情内容，不请求就不加载
//    测试获取文章列表
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")
    @GetMapping("/getfour")
@ApiOperation(value = "获取首页 4 篇文章列表",notes = "获取首页 4 篇文章列表")
@ApiImplicitParam(name = "",value = "",required = false,dataType = "List<Article")
  public  ResultData<List<Article>> getAllfour(){

//        分隔前 4 篇文章返回
          List<Article> articles = articleService.list();
          List<Article> rear = new ArrayList<>();
          for(int i = 0 ; i < 4 ; i ++)
          {
              rear.add(articles.get(i));
          }
        return ResultData.success(rear);
  }

//  获取所有博客的数量并返回
    @ApiOperation(value = "获取所有博客的数量并返回",notes = "获取所有博客的数量并返回")
    @ApiImplicitParam(name = "",value = "",required = false,dataType = "Long")
    @GetMapping("/getAllCount")
    public  ResultData<Long> getAll(){
        System.out.println(articleService.count());
        return ResultData.success(articleService.count());
    }

//  根据用户 id 拿到文章 , 用户个人中文展示的时候，需要用到这个
    @GetMapping("getbyid")
    @ApiOperation(value = "根据用户 id 拿到文章",notes = "根据用户 id 拿到文章")
    @ApiImplicitParam(paramType = "path",name = "id",value = "用户id",required = true,dataType = "Integer")
  public ResultData<List<Article>> getbyid(@PathVariable Integer id){
      LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
      queryWrapper.eq( Article::getId,id);
   return ResultData.success(articleService.list(queryWrapper));
  }

//  根据文章 id 拿到文章,从url 路由获取参数
    @GetMapping("/getOneById")
    @ApiOperation(value = "根据文章 id 拿到文章",notes = "根据文章 id 拿到文章")
    @ApiImplicitParam(paramType = "query",name = "id",value = "文章id",required = true,dataType = "Integer")
    public ResultData getarticlebyId(@RequestParam Integer id)
    {
        LambdaQueryWrapper<Article> queryWrapper= new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getArticleId,id);
        return ResultData.success(articleService.getOne(queryWrapper));
    }

//    分页查询
  @GetMapping("/page")
  @ApiOperation(value = "分页查询")
  @ApiImplicitParams({
          @ApiImplicitParam(paramType = "query",name = "page",value = "页码",required = true,dataType = "Page"),
          @ApiImplicitParam(paramType = "query",name = "size",value = "每页条数",required = true,dataType = "Page")
  })
  public ResultData<Page>  getrand(@RequestParam(value = "page")  int page ,@RequestParam(value = "pagesize") int pagesize){
       log.info("page = {} , pagesize = {}",page,pagesize);
//       构造分页构造器
      Page pageinfo = new Page(page,pagesize);
//      添加条件查询
// eg : 分页查询时根据更新 or 创建时间进行排序 ， 还可以添加根据名称或主题进行搜索查找
//      设置按时间降序
      pageinfo.setOrders(Collections.singletonList(OrderItem.desc("create_time")));
//      执行查询
      articleService.page(pageinfo);
      return ResultData.success(pageinfo);
  }

//  接收上传的文章
    @PutMapping("/putblog")
    @ApiOperation(value = "接收上传的文章")
    @ApiImplicitParam(paramType = "body",name = "article",value = "文章对象",required = true,dataType = "Article")
    public ResultData putblog(@RequestBody Articlereq articlereq, HttpServletRequest request)
    {
        Article article = new Article();
        article.setTitle(articlereq.getTitle());
        article.setArticleMd(articlereq.getContext());
        HttpSession session= request.getSession();
//        article.setId(Integer.valueOf((Integer)session.getAttribute("user")));
        article.setId(7);
        log.info("标题为："+articlereq.getTitle());
        log.info("内容为："+articlereq.getContext());
        articleService.save(article);
//        进行保存
        return ResultData.success("发布成功");
    }

//   查询返回所有文章的信息及对应作者的信息
//    接口测试返回数据正常
    @Autowired
    private UserService userService;
    @GetMapping("/getall")
    @ApiOperation(value = "查询返回所有文章的信息及对应作者的信息")
    @ApiImplicitParam(dataType = "List<Article>")
    @CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")   public ResultData getall(){
        List<Article> articles = articleService.list(); // 拿到所有文章
        List<User> users = userService.list();
        List<ReturnArticle> rear = new ArrayList<>();
        String[] names = new String[users.size()+2];

        for(User u: users)
        {
            names[u.getId()] = u.getName();
        }

        for(Article article: articles)
        {
//            对内容进行截取前四个字符
            String context = article.getArticleMd().substring(0,10)+"...";
//            进行赋值
            ReturnArticle returnArticle = new ReturnArticle(
                    names[article.getId()],
                    article.getArticleSummary(),
                    article.getTitle(),
                    article.getCreateTime(),
                    article.getLikeCount(),
                    article.getRemarkCount(),
                    article.getReadCount(),
                    context ,
                    article.getArticleId()
            );
            rear.add(returnArticle);
        }
        return ResultData.success(rear);

    }

//    根据传递进来的文字进行搜索标题
//    TODO: 后端接口正常，数据返回正常，前端异常
    @GetMapping("/search")
    @ApiOperation(value = "根据传递进来的文字进行搜索标题")
    @ApiImplicitParam(paramType = "query",name = "keyword",value = "文章标题",required = true,dataType = "List<Article>")
    public ResultData search(@RequestParam(value = "keyword") String keyword)
    {
//        解决特殊字符转义：eg: c++ ====> c
        keyword= URLDecoder.decode(keyword, StandardCharsets.UTF_8);
        keyword=keyword.trim();
        log.info("本次搜索的关键字为： "+keyword);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Article::getTitle,keyword);
        List<Article> articles = articleService.list(queryWrapper);
        log.info("搜索结果总数为： "+articles.size());
        return ResultData.success(articles);
    }

}




















