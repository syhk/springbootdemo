package com.example.syhk.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * 自定义元数据对象处理器
 */

@Slf4j
@Component
public class MyMetaObjecthandler implements MetaObjectHandler {


//    执行插入操作时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
//        这里自动填充的参数类型一定要实体类的相同，否则就会出异常
        log.info("公共字段 insert...");
        metaObject.setValue("createTime", LocalDate.now());
        metaObject.setValue("updateTime",LocalDate.now());
//        metaObject.setValue("push_time",new Date());
//        metaObject.setValue("create_time",new Date());
    }
//    执行操作时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段 update...");
//        metaObject.setValue("updateTime",LocalDate.now());
//        metaObject.setValue("push_time",new Date());
    }
}
