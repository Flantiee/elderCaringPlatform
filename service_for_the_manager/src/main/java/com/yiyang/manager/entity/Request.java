package com.yiyang.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Request implements Serializable {

    @TableId
    private Long requestID;

    private String type;

    private LocalDate startTime;

    private Long elderID;
}
