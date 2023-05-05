package com.yiyang.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyang.manager.entity.Request;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RequestMapper extends BaseMapper<Request> {
    @Select("select RequestID,type,StartTime,request.ElderID " +
            "from elders " +
            "left join request on elders.elderId = request.elderId " +
            "where elders.adminId = ${adminId};")
    public List<Request> selectAll(Long adminId);
}
