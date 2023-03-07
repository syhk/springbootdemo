package com.example.syhk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article_remark")
public class ArticleRemark implements Serializable {

//    remark 主键
    @TableField(value = "remark_id")
    @TableId(type = IdType.AUTO)
    Integer remark_id;

    @TableField(value = "id")
    Integer id;
    @TableField(value = "article_id")
    Integer ArticleId;

    @TableField(value = "context")
    String Context;
    @TableField(value = "createTime")
    LocalDate createTime=LocalDate.now();

    @TableField(value = "high")
    Integer high=0;

    @TableField(value = "low")
    Integer low=0;

}





















