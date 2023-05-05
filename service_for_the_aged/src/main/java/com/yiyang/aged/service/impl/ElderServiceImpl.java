package com.yiyang.aged.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.aged.entity.Elders;
import com.yiyang.aged.mapper.ElderMapper;
import com.yiyang.aged.service.ElderService;
import org.springframework.stereotype.Service;

@Service
public class ElderServiceImpl extends ServiceImpl<ElderMapper, Elders> implements ElderService {
}
