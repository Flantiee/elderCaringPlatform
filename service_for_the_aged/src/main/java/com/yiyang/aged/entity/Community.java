package com.yiyang.aged.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Community implements Serializable {

    @TableId
    private Long communityId;

    private String name;

    private String address;

    private String phone;
}
