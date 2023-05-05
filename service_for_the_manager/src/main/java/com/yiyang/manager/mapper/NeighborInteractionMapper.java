package com.yiyang.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyang.manager.dto.PieChartDto;
import com.yiyang.manager.entity.NeighborInteraction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NeighborInteractionMapper extends BaseMapper<NeighborInteraction> {
    @Select("select count(receiverID) as value,name " +
            "from elders " +
            "left join neighbor_interaction on elders.elderId=neighbor_interaction.receiverId " +
            "where elders.adminId=${adminId} group by name;")
    public List<PieChartDto> selectAll(Long adminId);
}
