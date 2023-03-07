package com.example.syhk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.syhk.entity.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
public  interface ArticleService extends IService<Article> {
    List<Article> getall();

}
