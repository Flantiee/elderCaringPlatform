package com.yiyang.aged.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.aged.common.R;
import com.yiyang.aged.entity.CommunityNews;
import com.yiyang.aged.service.CommunityNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/communitynews")
public class CommunityNewsController {
    @Autowired
    private CommunityNewsService communityNewsService;

    @GetMapping("/all")
    public R<List<CommunityNews>> all(@RequestParam("CommunityID") Long id) {
        LambdaQueryWrapper<CommunityNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommunityNews::getCommunityID,id).or().eq(CommunityNews::getCommunityID,0);
        List<CommunityNews> list = communityNewsService.list(queryWrapper);

        return R.success(list);
    }

    @GetMapping("/one")
    public R<CommunityNews> getOne(@RequestParam("CommunityNewsID") Long id) {
        LambdaQueryWrapper<CommunityNews> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommunityNews::getCommunityNewsID,id);
        CommunityNews one = communityNewsService.getOne(queryWrapper);

        return R.success(one);
    }
}
