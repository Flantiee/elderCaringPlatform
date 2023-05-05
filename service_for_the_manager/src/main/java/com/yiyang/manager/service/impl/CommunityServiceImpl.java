package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.dto.TypeDto;
import com.yiyang.manager.entity.Community;
import com.yiyang.manager.mapper.CommunityMapper;
import com.yiyang.manager.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public List<TypeDto> countType(Long adminId) {
        return communityMapper.countType(adminId);
    }
}
