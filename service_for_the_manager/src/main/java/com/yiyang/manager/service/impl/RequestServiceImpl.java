package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.entity.Request;
import com.yiyang.manager.mapper.RequestMapper;
import com.yiyang.manager.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements RequestService {

    @Autowired
    private RequestMapper requestMapper;


    @Override
    public List<Request> selectAll(Long adminId) {
        return requestMapper.selectAll(adminId);
    }
}
