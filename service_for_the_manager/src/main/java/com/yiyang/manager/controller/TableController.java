package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.dto.AvailableDto;
import com.yiyang.manager.dto.RelationDto;
import com.yiyang.manager.entity.*;
import com.yiyang.manager.service.*;
import com.yiyang.manager.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    private ElderService elderService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ChildrenService childrenService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private EmergencyEventsService emergencyEventsService;

    @Autowired
    private EldersChildrenRelationService eldersChildrenRelationService;

    @GetMapping("/elder")
    public R<List<Elders>> elderAll(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        LambdaQueryWrapper<Elders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Elders::getAdminId,adminId);

        List<Elders> list = elderService.list(queryWrapper);

        return R.success(list);
    }

    @PostMapping("/elder")
    public R<String> elderAdd(@RequestParam("name") String name, @RequestParam("sex") String sex, @RequestParam("phone") String phone,
                              @RequestParam("address") String address, @RequestParam("moveInTime") String moveInTime, @RequestParam("hobby") String hobby, HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        Admin byId = adminService.getById(adminId);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(moveInTime, fmt);

        Elders elders = new Elders();

        elders.setName(name);
        elders.setSex(sex);
        elders.setPhone(phone);
        elders.setAddress(address);
        elders.setMoveInTime(date);
        elders.setHobby(hobby);
        elders.setAdminId(adminId);
        elders.setCommunityId(byId.getCommunityId());

        elderService.save(elders);

        return R.success("成功");
    }

    @PostMapping("/elder/update")
    public R<String> elderUpdate(@RequestParam("name") String name, @RequestParam("sex") String sex, @RequestParam("phone") String phone, @RequestParam("address") String address,
                                 @RequestParam("moveInTime") String moveInTime, @RequestParam("hobby") String hobby,@RequestParam("elderId") Long elderId, HttpServletRequest request){
        Elders elders = new Elders();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(moveInTime, fmt);

        elders.setElderId(elderId);
        elders.setName(name);
        elders.setSex(sex);
        elders.setPhone(phone);
        elders.setAddress(address);
        elders.setMoveInTime(date);
        elders.setHobby(hobby);

        elderService.updateById(elders);


        return R.success("成功");
    }

    @DeleteMapping("/elder/delete")
    public R<String> elderDelete(@RequestParam("elderId") Long elderId){
        LambdaUpdateWrapper<Request> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Request::getElderID,elderId);
        updateWrapper.set(Request::getElderID,null);

        requestService.update(updateWrapper);

        LambdaUpdateWrapper<EmergencyEvents> updateWrapper1 = new LambdaUpdateWrapper<>();
        updateWrapper1.eq(EmergencyEvents::getElderId,elderId);
        updateWrapper1.set(EmergencyEvents::getElderId,elderId);

        emergencyEventsService.update(updateWrapper1);

        LambdaQueryWrapper<EldersChildrenRelation> updateWrapper2 = new LambdaQueryWrapper<>();
        updateWrapper2.eq(EldersChildrenRelation::getElderId,elderId);

        eldersChildrenRelationService.remove(updateWrapper2);

        elderService.removeById(elderId);
        return R.success("成功");
    }

    @GetMapping("/children")
    public R<List<Children>> childrenList(HttpServletRequest request) {
        //获取所有子女，无论是否有本社区的

        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        List<Children> childrenList = childrenService.childrenList(adminId);

        return R.success(childrenList);
    }

    @PostMapping("/children")
    public R<String> childrenAdd(@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("phone") String phone,
                                 @RequestParam("address") String address,@RequestParam("createTime") String createTime,@RequestParam("password") String password){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(createTime, fmt);

        Long pw = Long.parseLong(password);

        Children children = new Children();
        children.setName(name);
        children.setSex(sex);
        children.setPhone(phone);
        children.setAddress(address);
        children.setCreateTime(date);
        children.setPassword(pw);

        childrenService.save(children);

        return R.success("成功");
    }

    @PostMapping("/children/update")
    public R<String> childrenUpdate(@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("phone") String phone,@RequestParam("childrenId") Long childrenId,
                            @RequestParam("address") String address,@RequestParam("createTime") String createTime,@RequestParam("password") String password){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(createTime, fmt);

        Long pw = Long.parseLong(password);

        Children children = new Children();
        children.setChildrenId(childrenId);
        children.setName(name);
        children.setSex(sex);
        children.setPhone(phone);
        children.setAddress(address);
        children.setCreateTime(date);
        children.setPassword(pw);

        childrenService.updateById(children);

        return R.success("成功");
    }


    @DeleteMapping("/children/delete")
    public R<String> childrenDelete(@RequestParam("childrenId") Long childrenId) {

        LambdaQueryWrapper<EldersChildrenRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EldersChildrenRelation::getChildrenId,childrenId);

        eldersChildrenRelationService.remove(queryWrapper);

        childrenService.removeById(childrenId);

        return R.success("成功");
    }

    @GetMapping("/relations")
    public R<List<RelationDto>> relations(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        List<RelationDto> relationDto = elderService.selectRelation(adminId);

        return R.success(relationDto);
    }

    @PostMapping("/relations")
    public R<String> addRelation(@RequestParam("elderId") Long elderId, @RequestParam("childrenId") Long childrenId){

        EldersChildrenRelation eldersChildrenRelation = new EldersChildrenRelation();

        eldersChildrenRelation.setElderId(elderId);
        eldersChildrenRelation.setChildrenId(childrenId);

        eldersChildrenRelationService.save(eldersChildrenRelation);

        return R.success("成功");
    }

    @GetMapping("/relations/available")
    public R<AvailableDto> relationsAvailable(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        AvailableDto availableDto = new AvailableDto();

        Admin admin = adminService.getById(adminId);
        Long communityId = admin.getCommunityId();

        LambdaQueryWrapper<Elders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Elders::getCommunityId,communityId);

        List<Elders> eldersList = elderService.list(queryWrapper);
        availableDto.setElders(eldersList);

        List<Children> childrenList = childrenService.list();
        availableDto.setChildren(childrenList);

        return R.success(availableDto);
    }


    @PostMapping("/relations/update")
    public R<String> relationshipUpdate(@RequestParam("elderId") Long elderId,@RequestParam("childrenId") Long childrenId,@RequestParam("relationId") Long relationId){

        EldersChildrenRelation eldersChildrenRelation = new EldersChildrenRelation();
        eldersChildrenRelation.setRelationId(relationId);
        eldersChildrenRelation.setChildrenId(childrenId);
        eldersChildrenRelation.setElderId(elderId);

        eldersChildrenRelationService.updateById(eldersChildrenRelation);

        return R.success("成功");
    }


    @DeleteMapping("/relations/delete")
    public R<String> removeRelations(@RequestParam("relationId") Long relationId){

        eldersChildrenRelationService.removeById(relationId);

        return R.success("成功");
    }

}