package com.yiyang.manager.dto;

import lombok.Data;

@Data
public class LineChartDto {

    private LineChartsSonDto commityNews;

    private LineChartsSonDto interactions;

    private LineChartsSonDto imergencys;

    private LineChartsSonDto requests;
}
