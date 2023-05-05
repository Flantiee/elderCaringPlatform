package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.dto.*;
import com.yiyang.manager.entity.*;
import com.yiyang.manager.service.*;
import com.yiyang.manager.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommunityNewsService communityNewsService;

    @Autowired
    private NeighborInteractionService neighborInteractionService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private EmergencyEventsService emergencyEventsService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private CommunityService communityService;


    @GetMapping("/line")
    public R<LineChartDto> line(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        //填充第一个参数
        LineChartDto lineChartDto = new LineChartDto();
        LineChartsSonDto lineChartsSonDto = new LineChartsSonDto();

        LambdaQueryWrapper<CommunityNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommunityNews::getAdminID,adminId);
        List<CommunityNews> communityNewsList = communityNewsService.list(queryWrapper);

        for (CommunityNews communityNews:
             communityNewsList) {
            int dayOfYearMysql = communityNews.getAddTime().getYear();
            int dayOfMonthMysql = communityNews.getAddTime().getMonthValue();
            int dayOfYearNow = LocalDateTime.now().getYear();
            int dayOfMonthNow = LocalDateTime.now().getMonthValue();


            if((dayOfYearMysql == dayOfYearNow && dayOfMonthMysql == dayOfMonthNow-1)
                    || (dayOfYearMysql == dayOfYearNow-1 && dayOfMonthNow == 1 && dayOfMonthMysql == 12)){
                lineChartsSonDto.setPreviousMonth(communityNews.getAddTime().getDayOfWeek().getValue()-1);
            }

            if(dayOfYearMysql == dayOfYearNow && dayOfMonthMysql == dayOfMonthNow){
                lineChartsSonDto.setPresentMonth(communityNews.getAddTime().getDayOfWeek().getValue()-1);
            }
        }
        lineChartDto.setCommityNews(lineChartsSonDto);


        //填充第二个参数
        int[] previousMonth = new int[]{5,3,4,7,6,7,6};
        int[] presentMonth = new int[]{4,6,3,4,5,6,2};
        lineChartsSonDto = new LineChartsSonDto(previousMonth,presentMonth);
        lineChartDto.setInteractions(lineChartsSonDto);


        //填充第三个参数
        lineChartsSonDto = new LineChartsSonDto();
        List<EmergencyEvents> emergencyEventsList = emergencyEventsService.selectAll(adminId);
        for (EmergencyEvents emergencyEvent:
                emergencyEventsList) {
            int dayOfYearMysql = emergencyEvent.getStartTime().getYear();
            int dayOfMonthMysql = emergencyEvent.getStartTime().getMonthValue();
            int dayOfYearNow = LocalDateTime.now().getYear();
            int dayOfMonthNow = LocalDateTime.now().getMonthValue();


            if((dayOfYearMysql == dayOfYearNow && dayOfMonthMysql == dayOfMonthNow-1)
                    || (dayOfYearMysql == dayOfYearNow-1 && dayOfMonthNow == 1 && dayOfMonthMysql == 12)){
                lineChartsSonDto.setPreviousMonth(emergencyEvent.getStartTime().getDayOfWeek().getValue()-1);
            }

            if(dayOfYearMysql == dayOfYearNow && dayOfMonthMysql == dayOfMonthNow){
                lineChartsSonDto.setPresentMonth(emergencyEvent.getStartTime().getDayOfWeek().getValue()-1);
            }
        }
        lineChartDto.setImergencys(lineChartsSonDto);

        //第四个参数
        lineChartsSonDto = new LineChartsSonDto();
        List<Request> requestList = requestService.selectAll(adminId);
        for (Request requests:
                requestList) {
            int dayOfYearMysql = requests.getStartTime().getYear();
            int dayOfMonthMysql = requests.getStartTime().getMonthValue();
            int dayOfYearNow = LocalDateTime.now().getYear();
            int dayOfMonthNow = LocalDateTime.now().getMonthValue();

            if((dayOfYearMysql == dayOfYearNow && dayOfMonthMysql == dayOfMonthNow-1)
                    || (dayOfYearMysql == dayOfYearNow-1 && dayOfMonthNow == 1 && dayOfMonthMysql == 12)){
                lineChartsSonDto.setPreviousMonth(requests.getStartTime().getDayOfWeek().getValue()-1);
            }

            if(dayOfYearMysql == dayOfYearNow && dayOfMonthMysql == dayOfMonthNow){
                lineChartsSonDto.setPresentMonth(requests.getStartTime().getDayOfWeek().getValue()-1);
            }
        }
        lineChartDto.setRequests(lineChartsSonDto);

        return R.success(lineChartDto);
    }

    @GetMapping("/bar")
    public R<BarChartDto> bar(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        BarChartDto barChartDto = new BarChartDto();

        List<Request> requestList = requestService.selectAll(adminId);

        for (Request requests:
                requestList) {
            String type = requests.getType();
            if(type.equals("家庭医生") ){
                barChartDto.setFamilyDoc(requests.getStartTime().getDayOfWeek().getValue()-1);
            }
            if(type.equals("保洁卫生") ){
                barChartDto.setHouseClean(requests.getStartTime().getDayOfWeek().getValue()-1);
            }
            if(type.equals("助老服务") ){
                barChartDto.setElderService(requests.getStartTime().getDayOfWeek().getValue()-1);
            }
            if(type.equals("心理建设") ){
                barChartDto.setMentalHealth(requests.getStartTime().getDayOfWeek().getValue()-1);
            }
        }
        return R.success(barChartDto);
    }

    @GetMapping("/pie")
    public  R<List<PieChartDto>> pie(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        List<PieChartDto> pieChartDtoList = neighborInteractionService.selectAll(adminId);
        System.out.println(pieChartDtoList.toString());

        return R.success(pieChartDtoList);
    }

    @GetMapping("/raddar")
    public R<List<RaddarDto>> raddar(HttpServletRequest request){
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);

        List<TypeDto> typeDtos = communityService.countType(adminId);

        RaddarDto raddarDto = new RaddarDto();

        for (TypeDto typeDto:
             typeDtos) {
            if(typeDto.getType().equals("家庭医生")){
                raddarDto.setValue(0,typeDto.getNumber());
            }
            if(typeDto.getType().equals("保洁卫生")){
                raddarDto.setValue(1,typeDto.getNumber());
            }
            if(typeDto.getType().equals("助老服务")){
                raddarDto.setValue(2,typeDto.getNumber());
            }
            if(typeDto.getType().equals("心理建设")){
                raddarDto.setValue(3,typeDto.getNumber());
            }
        }

        Admin byId = adminService.getById(adminId);
        LambdaQueryWrapper<Community> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Community::getCommunityId,byId.getCommunityId());
        Community one = communityService.getOne(queryWrapper);

        raddarDto.setName(one.getName());


        //用一个列表来装(后端写的有缺陷)
        List <RaddarDto> jo = new ArrayList<RaddarDto>();
        jo.add(raddarDto);

        return R.success(jo);
    }

}
