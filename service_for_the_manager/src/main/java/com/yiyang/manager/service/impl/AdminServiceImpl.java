package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.entity.Admin;
import com.yiyang.manager.mapper.AdminMapper;
import com.yiyang.manager.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
