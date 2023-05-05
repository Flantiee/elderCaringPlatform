package com.yiyang.aged.dto;

import lombok.Data;

@Data
public class HobbyRequest {

    private Long elderId;

    private Boolean choosed;

    private String name;

    private String sex;

    private String community;

    private String hobby;

}
