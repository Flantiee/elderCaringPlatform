package com.yiyang.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

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
