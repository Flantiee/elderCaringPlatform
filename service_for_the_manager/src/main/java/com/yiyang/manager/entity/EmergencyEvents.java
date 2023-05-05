package com.yiyang.manager.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("emergencyevents")
public class EmergencyEvents implements Serializable {

    @TableId
    private Long emergencyEventsID;

    private int status;

    private String summary;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime startTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime finalTime;

    private Long elderId;

}
