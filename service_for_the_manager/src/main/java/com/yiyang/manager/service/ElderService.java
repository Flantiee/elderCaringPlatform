package com.yiyang.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.manager.dto.RelationDto;
import com.yiyang.manager.entity.Elders;

import java.util.List;

public interface ElderService extends IService<Elders> {
    public List<RelationDto> selectRelation(Long adminId);
}
