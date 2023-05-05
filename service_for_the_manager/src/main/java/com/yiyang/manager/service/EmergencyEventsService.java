package com.yiyang.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.manager.dto.EmergencyAllDto;
import com.yiyang.manager.entity.EmergencyEvents;

import java.util.List;

public interface EmergencyEventsService extends IService<EmergencyEvents> {

    public List<EmergencyEvents> selectAll(Long adminID);

    public List<EmergencyAllDto> allCommunity(Long communityID);
}
