package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.entity.Elders;
import com.yiyang.manager.service.ElderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/elder")
public class ElderController {

    @Autowired
    private ElderService elderService;

    @PostMapping("/login")
    public R<Elders> login(HttpServletRequest request, @RequestBody Elders elder){

        LambdaQueryWrapper<Elders> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Elders::getPhone,elder.getPhone());
        Elders one = elderService.getOne(queryWrapper);

        if(one == null){
            return R.error("账号不正确");
        }

        request.getSession().setAttribute("elder",one.getElderId());
        return R.success(one);
    }

}
