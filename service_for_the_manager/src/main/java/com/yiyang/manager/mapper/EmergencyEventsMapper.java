package com.yiyang.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyang.manager.dto.EmergencyAllDto;
import com.yiyang.manager.entity.EmergencyEvents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmergencyEventsMapper extends BaseMapper<EmergencyEvents> {
    @Select("select EmergencyEventsID,Status,Summary,StartTime,FinalTime,emergencyevents.elderID " +
            "from elders " +
            "left join emergencyevents on elders.ElderID = emergencyevents.ElderID " +
            "where elders.adminID = ${adminID};")
    public List<EmergencyEvents> selectAll(Long adminID);

    @Select("select emergencyEventsID,status,summary,startTime,finalTime,elders.name as elderName,elders.elderID " +
            "from elders right join emergencyevents on elders.elderId = emergencyevents.elderId where CommunityID = ${communityID};")
    public List<EmergencyAllDto> allCommunity(Long communityID);
}
