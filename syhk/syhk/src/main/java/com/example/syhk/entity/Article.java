package com.example.syhk.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * TODO:
 *  blog Entity
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */

// @EntityScan 用来扫描和发现指定包及其子包中的 Entity 定义
//@EntityScan
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
public class Article  implements Serializable {

//     文章 id
    @TableId(type = IdType.AUTO)
    @TableField(value = "article_id")
//    private Integer article_id;
    private Integer ArticleId;

//     发表日期
    @TableField(value = "push_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    private Date push_time;
    private LocalDate PushTime=LocalDate.now();

//    发表用户id
    @TableField(value = "id")
//    private Integer id;
    private Integer Id;

//     主题
    @TableField(value = "title")
//    private String title;
    private String Title;

    //    md 格式
    @TableField(value = "md")
    private String ArticleMd;


//    点赞数
    @TableField(value = "like_count")
//    private Integer like_count = 0;
    private Integer LikeCount = 0;

//     评论数
    @TableField(value = "remark_count")
//    private  Integer remark_count = 0;
//    记录问题： java 字段名 不要写带有下划线的，要写成 java 的大小写命名方式，不要有下划线，不然会出问题
    private  Integer RemarkCount = 0;

//     访问量
    @TableField(value = "read_count")
//    private Integer read_count = 0;
    private Integer ReadCount = 0;

//    创建时间
    @TableField(value = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:sss")
//    private  Date create_time ;
    private LocalDate CreateTime = LocalDate.now();

//     文章摘要
    @TableField(value = "article_summary")
//    private String article_summary = "";
    private String ArticleSummary = "";



}



























