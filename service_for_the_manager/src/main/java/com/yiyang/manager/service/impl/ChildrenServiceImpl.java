package com.yiyang.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyang.manager.entity.Children;
import com.yiyang.manager.mapper.ChildrenMapper;
import com.yiyang.manager.service.ChildrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildrenServiceImpl extends ServiceImpl<ChildrenMapper, Children> implements ChildrenService {
    @Autowired
    private ChildrenMapper childrenMapper;

    @Override
    public List<Children> childrenList(Long adminId) {
        return childrenMapper.childrenList(adminId);
    }
}
