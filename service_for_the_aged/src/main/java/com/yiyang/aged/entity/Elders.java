package com.yiyang.aged.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Elders implements Serializable {

    @TableId
    private Long elderId;

    private String name;

    private String sex;

    private String phone;

    private String address;

    private LocalDate moveInTime;

    private String hobby;

    private int theme;

    private Long adminId;

    private Long communityId;

}
