package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.dto.EmergencyAllDto;
import com.yiyang.manager.entity.Admin;
import com.yiyang.manager.entity.CommunityNews;
import com.yiyang.manager.entity.EmergencyEvents;
import com.yiyang.manager.service.AdminService;
import com.yiyang.manager.service.CommunityNewsService;
import com.yiyang.manager.service.EmergencyEventsService;
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
@RequestMapping("/affairs")
public class AffairsController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommunityNewsService communityNewsService;

    @Autowired
    private EmergencyEventsService emergencyEventsService;

    @GetMapping("/news")
    public R<List<CommunityNews>> newsAll(HttpServletRequest request) {
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        Admin admin = adminService.getById(adminId);

        LambdaQueryWrapper<CommunityNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommunityNews::getCommunityID,admin.getCommunityId());

        List<CommunityNews> list = communityNewsService.list(queryWrapper);

        return R.success(list);
    }

    @PostMapping("/news")
    public R<String> newsAdd(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("content") String content,
                             @RequestParam("addTime") String addTime, @RequestParam("img") String img){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(addTime, fmt);

        Admin admin = adminService.getById(adminId);

        CommunityNews communityNews = new CommunityNews();
        communityNews.setTitle(title);
        communityNews.setContent(content);
        communityNews.setAddTime(date);
        communityNews.setImg(img);
        communityNews.setAdminID(adminId);
        communityNews.setCommunityID(admin.getCommunityId());

        communityNewsService.save(communityNews);

        return R.success("成功");
    }

    @DeleteMapping("/news/delete")
    public R<String> newsDelete(@RequestParam("communityNewsID") Long communityNewsID){

        communityNewsService.removeById(communityNewsID);

        return R.success("成功");
    }

    @PostMapping("/news/update")
    public R<String> newsUpdate(@RequestParam("communityNewsID") Long communityNewsID,@RequestParam("title") String title,
                                @RequestParam("content") String content,@RequestParam("addTime") String addTime,@RequestParam("img") String img){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(addTime, fmt);

        CommunityNews communityNews = new CommunityNews();
        communityNews.setCommunityNewsID(communityNewsID);
        communityNews.setTitle(title);
        communityNews.setContent(content);
        communityNews.setAddTime(date);
        communityNews.setImg(img);

        communityNewsService.updateById(communityNews);

        return R.success("成功");
    }

    @GetMapping("/emergency")
    public R<List<EmergencyAllDto>> emergencyList(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        Admin admin = adminService.getById(adminId);

        List<EmergencyAllDto> emergencyAllDto = emergencyEventsService.allCommunity(admin.getCommunityId());

        return R.success(emergencyAllDto);
    }

    @PostMapping("/emergency/conclude")
    public R<String> conclude(@RequestParam("summary") String summary, @RequestParam("emergencyEventsID") Long emergencyEventsID){

        LambdaUpdateWrapper<EmergencyEvents> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(EmergencyEvents::getEmergencyEventsID,emergencyEventsID);
        updateWrapper.set(EmergencyEvents::getSummary,summary);

        emergencyEventsService.update(updateWrapper);

        return R.success("成功");
    }

    @PostMapping("/emergency/status")
    public R<String> statusUpdate(@RequestParam("emergencyEventsID") Long emergencyEventsID){

        EmergencyEvents emergencyEvents = emergencyEventsService.getById(emergencyEventsID);

        int status = emergencyEvents.getStatus();

        if (status == 2){
            emergencyEvents.setStatus(0);
            emergencyEventsService.updateById(emergencyEvents);
        }else if (status == 0){
            emergencyEvents.setStatus(2);
            emergencyEventsService.updateById(emergencyEvents);
        }

        return R.success("成功");
    }

}
