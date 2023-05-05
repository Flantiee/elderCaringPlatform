package com.yiyang.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.manager.dto.TypeDto;
import com.yiyang.manager.entity.Community;

import java.util.List;

public interface CommunityService extends IService<Community> {
    public List<TypeDto> countType(Long adminId);
}
