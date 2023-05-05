package com.yiyang.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiyang.manager.entity.Children;

import java.util.List;

public interface ChildrenService extends IService<Children> {
    public List<Children> childrenList(Long adminId);
}
