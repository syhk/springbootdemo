package com.example.syhk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_detail")
public class ArticleDetail implements Serializable {
//     文章详情 id
    @TableId(type = IdType.AUTO)
    private Integer ArticleDetailId;

//      文章 id
    private Integer ArticleId;

//    html 格式
    private String ArticleHtml;


}
