package com.yiyang.aged.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.aged.entity.CommunityNews;
import com.yiyang.aged.mapper.CommunityNewsMapper;
import com.yiyang.aged.service.CommunityNewsService;
import org.springframework.stereotype.Service;

@Service
public class CommunityNewsServiceImpl extends ServiceImpl<CommunityNewsMapper, CommunityNews> implements CommunityNewsService {
}
