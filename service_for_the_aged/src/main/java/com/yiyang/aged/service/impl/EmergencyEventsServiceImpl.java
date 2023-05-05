package com.yiyang.aged.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.aged.entity.EmergencyEvents;
import com.yiyang.aged.mapper.EmergencyEventsMapper;
import com.yiyang.aged.service.EmergencyEventsService;
import org.springframework.stereotype.Service;

@Service
public class EmergencyEventsServiceImpl extends ServiceImpl<EmergencyEventsMapper, EmergencyEvents> implements EmergencyEventsService {
}
