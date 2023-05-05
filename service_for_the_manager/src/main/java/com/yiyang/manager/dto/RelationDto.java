package com.yiyang.manager.dto;

import lombok.Data;

@Data
public class RelationDto {
    private Long relationID;

    private Long elderID;

    private String elderName;

    private String elderSex;

    private String elderAddress;

    private Long childrenID;

    private String childrenName;

    private String childrenPhone;
}
