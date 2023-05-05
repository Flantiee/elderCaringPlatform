package com.yiyang.aged.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.aged.entity.Community;
import com.yiyang.aged.mapper.CommunityMapper;
import com.yiyang.aged.service.CommunityService;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {
}
