package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.dto.EmergencyAllDto;
import com.yiyang.manager.entity.EmergencyEvents;
import com.yiyang.manager.mapper.EmergencyEventsMapper;
import com.yiyang.manager.service.EmergencyEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyEventsServiceImpl extends ServiceImpl<EmergencyEventsMapper, EmergencyEvents> implements EmergencyEventsService {
    @Autowired
    private EmergencyEventsMapper emergencyEventsMapper;

    @Override
    public List<EmergencyEvents> selectAll(Long adminID) {

        return emergencyEventsMapper.selectAll(adminID);
    }

    @Override
    public List<EmergencyAllDto> allCommunity(Long communityID) {
        return emergencyEventsMapper.allCommunity(communityID);
    }
}
