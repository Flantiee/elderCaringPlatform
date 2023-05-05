package com.yiyang.aged.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("communitynews")
public class CommunityNews implements Serializable {

    @TableId
    private Long communityNewsID;

    private String title;

    private int type;

    private String content;

    private LocalDate addTime;

    private Long adminID;

    private Long communityID;

    private String img;
}
