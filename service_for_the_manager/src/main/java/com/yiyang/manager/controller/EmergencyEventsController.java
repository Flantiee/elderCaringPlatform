package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.entity.EmergencyEvents;
import com.yiyang.manager.service.EmergencyEventsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/emergencyevents")
public class EmergencyEventsController {
    @Autowired
    private EmergencyEventsService emergencyEventsService;

    @PostMapping("/add")
    public R<String> add(@RequestParam("ElderID") Long elderID){
        EmergencyEvents emergencyEvents = new EmergencyEvents();
        emergencyEvents.setStatus(0);
        emergencyEvents.setElderId(elderID);
        emergencyEventsService.save(emergencyEvents);
        return R.success("成功");
    }

    @PostMapping("/cancel")
    public R<String> cancel(@RequestParam("ElderID") Long elderID){
        LambdaQueryWrapper<EmergencyEvents> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmergencyEvents::getElderId,elderID);
        queryWrapper.eq(EmergencyEvents::getStatus,0);
        EmergencyEvents one = emergencyEventsService.getOne(queryWrapper);

//        LambdaUpdateWrapper<EmergencyEvents> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.eq(EmergencyEvents::getEmergencyEventsID,one.getEmergencyEventsID());
//        updateWrapper.set(,1);

        one.setStatus(1);
        emergencyEventsService.updateById(one);

        return R.success("成功");
    }
}
