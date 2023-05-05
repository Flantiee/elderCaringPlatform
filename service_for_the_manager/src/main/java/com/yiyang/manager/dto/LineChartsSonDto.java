package com.yiyang.manager.dto;

import lombok.Data;

@Data
public class LineChartsSonDto {

    private int[] previousMonth;

    private int[] presentMonth;

    public LineChartsSonDto(){
        this.previousMonth = new int[7];
        this.presentMonth = new int[7];
    }

    public LineChartsSonDto(int[] previousMonth,int[] presentMonth){
        this.previousMonth = previousMonth;
        this.presentMonth = presentMonth;
    }

    public void setPreviousMonth(int i){
        this.previousMonth[i]++;
    }

    public void setPresentMonth(int i){
        this.presentMonth[i]++;
    }
 }
