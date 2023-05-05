package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.dto.HobbyRequest;
import com.yiyang.manager.dto.InteractionRequest;
import com.yiyang.manager.entity.Community;
import com.yiyang.manager.entity.Elders;
import com.yiyang.manager.entity.NeighborInteraction;
import com.yiyang.manager.service.CommunityService;
import com.yiyang.manager.service.ElderService;
import com.yiyang.manager.service.NeighborInteractionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/neighborinteraction")
public class NeighborInteractionController {
    @Autowired
    private ElderService elderService;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private NeighborInteractionService neighborInteractionService;

    @GetMapping("/list")
    public R<List<HobbyRequest>> list(@RequestParam("ElderID") Long elderId) {
        LambdaQueryWrapper<Elders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Elders::getElderId,elderId);
        Elders one = elderService.getOne(queryWrapper);

        String hobby = one.getHobby();

        LambdaQueryWrapper<Elders> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Elders::getHobby,hobby);
        queryWrapper1.ne(Elders::getElderId,elderId);
        List<Elders> list = elderService.list(queryWrapper1);

        List<HobbyRequest> hobbyRequests = new ArrayList<>();

        for (Elders i:
             list) {
            HobbyRequest hobbyRequest = new HobbyRequest();
            hobbyRequest.setElderId(i.getElderId());
            hobbyRequest.setName(i.getName());
            hobbyRequest.setSex(i.getSex());
            hobbyRequest.setHobby(i.getHobby());

            LambdaQueryWrapper<Community> communityLambdaQueryWrapper = new LambdaQueryWrapper<>();
            communityLambdaQueryWrapper.eq(Community::getCommunityId,i.getCommunityId());
            Community one1 = communityService.getOne(communityLambdaQueryWrapper);

            hobbyRequest.setCommunity(one1.getName());

            LambdaQueryWrapper<NeighborInteraction> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(NeighborInteraction::getInitiatorId,elderId);
            queryWrapper2.eq(NeighborInteraction::getReceiverId,i.getElderId());
            NeighborInteraction one2 = neighborInteractionService.getOne(queryWrapper2);

            if(one2==null){
                hobbyRequest.setChoosed(false);
            }else {
                hobbyRequest.setChoosed(true);
            }

            hobbyRequests.add(hobbyRequest);
        }

        return R.success(hobbyRequests);
    }

    @PostMapping("/want")
    public R<String> want(@RequestParam("TargetElderID") Long targetElderID,@RequestParam("ElderID") Long elderID){
        LambdaQueryWrapper<Elders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Elders::getElderId,targetElderID).or().eq(Elders::getElderId,elderID);
        List<Elders> list = elderService.list(queryWrapper);

        NeighborInteraction neighborInteraction = new NeighborInteraction();

        String time = LocalDateTime.now().toString();
        String timeId = "";
        for (int i = time.length()-1; i >= 0; i--) {
            if(time.charAt(i) >= 48 && time.charAt(i) <= 57 && timeId.length()<9) {
                timeId += time.charAt(i);
            }
        }

        neighborInteraction.setInteractionID(Integer.parseInt(timeId));

        for (Elders i:
             list) {
            if (i.getElderId()==targetElderID){
                neighborInteraction.setReceiver(i.getName());
                neighborInteraction.setReceiverId(i.getElderId());
            }else {
                neighborInteraction.setInitiator(i.getName());
                neighborInteraction.setInitiatorId(i.getElderId());
            }
        }
        neighborInteraction.setReceiverAccepted(0);

        neighborInteractionService.save(neighborInteraction);

        return R.success("成功");
    }

    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam("TargetElderID") Long targetElderID,@RequestParam("ElderID") Long elderID){

        LambdaQueryWrapper<NeighborInteraction> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NeighborInteraction::getInitiatorId,elderID);
        queryWrapper.eq(NeighborInteraction::getReceiverId,targetElderID);

        neighborInteractionService.remove(queryWrapper);

        return R.success("成功");
    }

    @GetMapping("/interactionall")
    public R<List<InteractionRequest>> interactionAll(@RequestParam("ElderID") Long elderId){
        LambdaQueryWrapper<NeighborInteraction> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NeighborInteraction::getReceiverId,elderId);
        List<NeighborInteraction> list = neighborInteractionService.list(queryWrapper);

        List<InteractionRequest> requests = new ArrayList<>();

        for (NeighborInteraction i:
             list) {
            InteractionRequest request = new InteractionRequest();
            request.setInteractionId(i.getInteractionID());
            request.setInitiator(i.getInitiator());
            request.setInitiatorId(i.getInitiatorId());
            request.setReceiverAccepted(i.getReceiverAccepted());

            LambdaQueryWrapper<Elders> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Elders::getElderId,i.getInitiatorId());
            Elders one = elderService.getOne(queryWrapper1);
            request.setInitiatorAddress(one.getAddress());

            requests.add(request);
        }

        return R.success(requests);
    }

    @PostMapping("/ok")
    public R<String> ok(@RequestParam("TargetElderID") Long targetElderID,@RequestParam("ElderID") Long elderID){

        LambdaUpdateWrapper<NeighborInteraction> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(NeighborInteraction::getInitiatorId,targetElderID);
        updateWrapper.eq(NeighborInteraction::getReceiverId,elderID);
        updateWrapper.set(NeighborInteraction::getReceiverAccepted,1);

        neighborInteractionService.update(updateWrapper);

        return R.success("成功");
    }
}
