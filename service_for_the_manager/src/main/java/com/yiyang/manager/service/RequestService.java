package com.yiyang.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.manager.entity.Request;

import java.util.List;

public interface RequestService extends IService<Request> {

    public List<Request> selectAll(Long adminId);
}
