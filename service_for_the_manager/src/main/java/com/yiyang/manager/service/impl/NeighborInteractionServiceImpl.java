package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.dto.PieChartDto;
import com.yiyang.manager.entity.NeighborInteraction;
import com.yiyang.manager.mapper.NeighborInteractionMapper;
import com.yiyang.manager.service.NeighborInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeighborInteractionServiceImpl extends ServiceImpl<NeighborInteractionMapper, NeighborInteraction> implements NeighborInteractionService {

    @Autowired
    private NeighborInteractionMapper neighborInteractionMapper;

    @Override
    public List<PieChartDto> selectAll(Long adminId) {
        return neighborInteractionMapper.selectAll(adminId);
    }
}
