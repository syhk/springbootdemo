package com.example.syhk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.syhk.dao.ArticleRemarkMapper;
import com.example.syhk.entity.ArticleRemark;
import com.example.syhk.service.ArticleRemarkService;
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
public class ArticleRemarkImpl extends ServiceImpl<ArticleRemarkMapper, ArticleRemark> implements ArticleRemarkService {
    @Autowired
    ArticleRemarkMapper articleRemarkMapper;
}
