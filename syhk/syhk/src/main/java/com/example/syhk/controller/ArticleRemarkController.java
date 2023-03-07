package com.example.syhk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.syhk.common.ResultData;
import com.example.syhk.entity.ArticleRemark;
import com.example.syhk.entity.User;
import com.example.syhk.entity.reentity.ReRemark;
import com.example.syhk.service.ArticleRemarkService;
import com.example.syhk.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/remark")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true",allowedHeaders ="*")
@Api
public class ArticleRemarkController {


    @Resource
    ArticleRemarkService articleRemarkService;
    @Autowired
    UserService userService;
    private final String base="http://localhost:8800/user/downheadpor?name=";

//    根据文章 id 拿取指定评论
    @ApiOperation(value = "根据文章 id 拿取指定评论")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Integer", paramType = "query")
    @GetMapping("/getReamByid")
    public ResultData<List<ReRemark>> getReamByid(@RequestParam Integer id)
    {
      LambdaQueryWrapper<ArticleRemark> lambdaQueryWrapper=new LambdaQueryWrapper<>();
//      根据时间降序
        lambdaQueryWrapper.eq(ArticleRemark::getArticleId,id).orderByDesc(ArticleRemark::getCreateTime);
        List<ArticleRemark> articleRemarks = articleRemarkService.list(lambdaQueryWrapper);
        List<ReRemark> reRemarks = new ArrayList<>();
        for(ArticleRemark articleRemark:articleRemarks)
        {
            ReRemark reRemark = new ReRemark();
            reRemark.setId(articleRemark.getId());
            reRemark.setArticleId(articleRemark.getArticleId());
            reRemark.setContext(articleRemark.getContext());
            reRemark.setCreateTime(articleRemark.getCreateTime());
//            查询用户信息
            User user = userService.getById(articleRemark.getId());
            reRemark.setName(user.getName());
            reRemark.setAvatarurl(base+user.getAvatarUrl());
            reRemarks.add(reRemark);
        }
        return ResultData.success(reRemarks);
    }
// 接口测试正常

//    提交评论
    @PostMapping("/submitremark")
    @ApiOperation(value = "提交评论")
    @ApiImplicitParam(name = "articleRemark", value = "评论内容", required = true, dataType = "bool", paramType = "body")
    public ResultData submitremark(@RequestBody ArticleRemark articleRemark )
    {
      System.out.println("评论主体是："+articleRemark.toString());
      ArticleRemark articleRemark1= new ArticleRemark();
      articleRemark1.setArticleId(articleRemark.getArticleId());
      articleRemark1.setId(articleRemark.getId());
      articleRemark1.setContext(articleRemark.getContext());
      articleRemark1.setHigh(0);
      articleRemark1.setLow(0);
        articleRemarkService.save(articleRemark1);
        return ResultData.success(articleRemark1);
    }



}



























