package com.yiyang.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Admin implements Serializable {

    @TableId
    private Long AdminId;

    private String name;

    private int password;

    private String phone;

    private LocalDate hireDate;

    private int authority;

    private Long communityId;
}
