package com.yiyang.manager.dto;

import lombok.Data;

@Data
public class BarChartDto {

    private int[] familyDoc;

    private int[] houseClean;

    private int[] elderService;

    private int[] mentalHealth;

    public BarChartDto(){
        this.familyDoc = new int[7];
        this.houseClean = new int[7];
        this.elderService = new int[7];
        this.mentalHealth = new int[7];
    }

    public void setFamilyDoc(int i) {
        this.familyDoc[i]++;
    }

    public void setHouseClean(int i) {
        this.houseClean[i]++;
    }

    public void setElderService(int i) {
        this.elderService[i]++;
    }

    public void setMentalHealth(int i) {
        this.mentalHealth[i]++;
    }
}
