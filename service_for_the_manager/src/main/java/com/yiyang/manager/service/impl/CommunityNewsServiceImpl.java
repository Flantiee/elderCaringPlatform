package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.entity.CommunityNews;
import com.yiyang.manager.mapper.CommunityNewsMapper;
import com.yiyang.manager.service.CommunityNewsService;
import org.springframework.stereotype.Service;

@Service
public class CommunityNewsServiceImpl extends ServiceImpl<CommunityNewsMapper, CommunityNews> implements CommunityNewsService {
}
