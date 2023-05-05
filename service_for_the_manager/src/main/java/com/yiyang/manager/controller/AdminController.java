package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.dto.AdminDto;
import com.yiyang.manager.entity.Admin;
import com.yiyang.manager.entity.Community;
import com.yiyang.manager.service.AdminService;
import com.yiyang.manager.service.CommunityService;
import com.yiyang.manager.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@Slf4j
@RestController
@RequestMapping("/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommunityService communityService;


    @PostMapping("/login")
    public R<String> login(@RequestParam("phone") String phone, @RequestParam("password") String password,HttpServletRequest request){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getPhone,phone);
        queryWrapper.eq(Admin::getPassword,password);

        Admin one = adminService.getOne(queryWrapper);

        if(one==null){
            return R.error("失败");
        }

        String token = JWTUtil.createToken(one.getAdminId().toString(), one.getName());

        request.getSession().setAttribute("user",token);


        return R.success(token);
    }

    @GetMapping("/info")
    public R<AdminDto> info(HttpServletRequest request) {
        String token = request.getHeader("token");
        Long adminId = JWTUtil.verify(token);
        Admin admin = adminService.getById(adminId);
        Long communityId = admin.getCommunityId();
        Community community = communityService.getById(communityId);

        AdminDto adminDto = new AdminDto();

        adminDto.setCommunity(community.getName());
        adminDto.setName(admin.getName());

        return R.success(adminDto);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {

        request.getSession().removeAttribute("user");

        return R.success("成功");
    }
}
