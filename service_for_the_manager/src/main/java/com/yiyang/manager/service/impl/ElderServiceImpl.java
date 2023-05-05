package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.dto.RelationDto;
import com.yiyang.manager.entity.Elders;
import com.yiyang.manager.mapper.ElderMapper;
import com.yiyang.manager.service.ElderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderServiceImpl extends ServiceImpl<ElderMapper, Elders> implements ElderService {
    @Autowired
    private ElderMapper elderMapper;

    @Override
    public List<RelationDto> selectRelation(Long adminId) {
        return elderMapper.selectRelation(adminId);
    }
}
