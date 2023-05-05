package com.yiyang.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyang.manager.common.R;
import com.yiyang.manager.entity.Request;
import com.yiyang.manager.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/add")
    public R<String> add(@RequestParam("Type") String type, @RequestParam("ElderID") Long eldID, @RequestParam("StartDate") String startDate) {
        Request request = new Request();

        String time = LocalDateTime.now().toString();
        String timeId = "";
        for (int i = time.length()-1; i >= 0; i--) {
            if(time.charAt(i) >= 48 && time.charAt(i) <= 57 && timeId.length()<9) {
                timeId += time.charAt(i);
            }
        }

        request.setRequestID((long) Integer.parseInt(timeId));

        request.setType(type);
        request.setElderID(eldID);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateParam = LocalDate.parse(startDate, df);

        request.setStartTime(dateParam);

        requestService.save(request);

        return R.success("成功");
    }

    @GetMapping("/all")
    public R<List<Request>> all(@RequestParam("ElderID") Long eldID) {
        LambdaQueryWrapper<Request> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Request::getElderID,eldID);
        List<Request> list = requestService.list(queryWrapper);

        return R.success(list);
    }

    @DeleteMapping("/del")
    public R<String> del(@RequestParam("requestID") Long requestId){
        requestService.removeById(requestId);

        return R.success("成功");
    }
}
