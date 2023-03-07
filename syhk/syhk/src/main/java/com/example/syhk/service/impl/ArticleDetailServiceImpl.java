package com.example.syhk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.syhk.dao.ArticleDetailMapper;
import com.example.syhk.entity.ArticleDetail;
import com.example.syhk.service.ArticleDetailService;
import com.example.syhk.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO:
 *
 * @author syhk
 * @date
 * @update_by syhk
 * @update_at
 */
@Service
public class ArticleDetailServiceImpl extends ServiceImpl<ArticleDetailMapper, ArticleDetail>  implements ArticleDetailService{
    @Autowired
    ArticleDetailMapper articleDetailMapper;
}
