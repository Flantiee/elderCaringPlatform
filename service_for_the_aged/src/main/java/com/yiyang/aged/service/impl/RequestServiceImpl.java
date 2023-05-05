package com.yiyang.aged.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.aged.entity.Request;
import com.yiyang.aged.mapper.RequestMapper;
import com.yiyang.aged.service.RequestService;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements RequestService {
}
