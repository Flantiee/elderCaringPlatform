package com.yiyang.aged.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.aged.common.R;
import com.yiyang.aged.entity.Elders;
import com.yiyang.aged.service.ElderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
