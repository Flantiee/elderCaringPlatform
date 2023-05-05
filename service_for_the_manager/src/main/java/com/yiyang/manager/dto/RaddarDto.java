package com.yiyang.manager.dto;

import lombok.Data;

@Data
public class RaddarDto {
    private int[] value;

    private String name;

    public RaddarDto(){
        this.value = new int[4];
    }

    public void setValue(int i,int number){
        this.value[i]=number;
    }
}
