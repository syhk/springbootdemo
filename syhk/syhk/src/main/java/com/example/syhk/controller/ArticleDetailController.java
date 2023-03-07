package com.example.syhk.controller;

import com.example.syhk.common.ResultData;
import com.example.syhk.entity.ArticleDetail;
import com.example.syhk.service.ArticleDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/detail")
public class ArticleDetailController {

    @Autowired
    ArticleDetailService articleDetailService;
//    获取所有文章
    @GetMapping("/getall")
    public ResultData<List<ArticleDetail>> getalldetail(){
        return  ResultData.success(articleDetailService.list());
    }
//    根据所属性文章 id 查询对应详情文章
    @GetMapping("/getbyid")
    public ResultData<ArticleDetail> getbyid(Integer id){
        return ResultData.success(articleDetailService.getById(id));
    }

//    新增
    @PostMapping("/add")
    public ResultData<Boolean> add(@RequestBody ArticleDetail articleDetail)
    {
        return ResultData.success(articleDetailService.save(articleDetail));
    }
//    删除
    @DeleteMapping("/del/{id}")
    public ResultData<Boolean> del(@PathVariable Integer id){
       return ResultData.success(articleDetailService.removeById(id));
    }
//    更新
    @PutMapping("/update")
    public ResultData update(@RequestBody ArticleDetail articleDetail)
    {
        return ResultData.success(articleDetailService.updateById(articleDetail));
    }





}
