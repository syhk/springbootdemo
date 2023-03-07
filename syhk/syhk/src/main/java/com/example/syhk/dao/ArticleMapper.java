package com.example.syhk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.syhk.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Mapper
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select  * FROM `article`;")
    List<Article> getAll();

}
