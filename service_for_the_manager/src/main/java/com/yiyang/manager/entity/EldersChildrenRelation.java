package com.yiyang.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("elders_children_relation")
public class EldersChildrenRelation {

    @TableId
    private Long relationId;

    private Long elderId;

    private Long childrenId;
}
