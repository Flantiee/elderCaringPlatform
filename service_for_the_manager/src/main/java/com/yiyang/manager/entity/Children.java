package com.yiyang.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Children implements Serializable {
    @TableId
    private Long ChildrenId;

    private String name;

    private Long password;

    private String sex;

    private String phone;

    private String address;

    private LocalDate createTime;
}
