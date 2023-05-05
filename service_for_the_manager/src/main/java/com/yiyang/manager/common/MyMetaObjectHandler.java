package com.yiyang.manager.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入的时候自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充【insert】。。。。");
        log.info(metaObject.toString());
        metaObject.setValue("startTime", LocalDateTime.now());
//        metaObject.setValue("finalTime", LocalDateTime.now());
//        metaObject.setValue("startUser", BaseContext.getCurrentId());
//        metaObject.setValue("finalUser", BaseContext.getCurrentId());
    }

    /**
     * 更新的时候自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充【update】。。。。");
        log.info(metaObject.toString());

        metaObject.setValue("finalTime", LocalDateTime.now());
//        metaObject.setValue("finalUser", BaseContext.getCurrentId());
    }
}
