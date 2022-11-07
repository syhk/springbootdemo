package com.example.syhk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.syhk.entity.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Repository
@Mapper
public interface ArticleDetailMapper extends BaseMapper<ArticleDetail> {
}
