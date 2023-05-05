package com.yiyang.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.manager.dto.PieChartDto;
import com.yiyang.manager.entity.NeighborInteraction;

import java.util.List;

public interface NeighborInteractionService extends IService<NeighborInteraction> {
    public List<PieChartDto> selectAll(Long adminId);
}
