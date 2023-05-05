package com.yiyang.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyang.manager.dto.RelationDto;
import com.yiyang.manager.entity.Elders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ElderMapper extends BaseMapper<Elders> {
    @Select("select relationID,elderID,ece.name as elderName,ece.sex as elderSex,ece.address as elderAddress,children.childrenID,children.name as childrenName,phone as childrenPhone \n" +
            "from (select RelationID,ec.elderID,name,sex,address,childrenID,adminID from elders_children_relation as ec left join elders on ec.elderId = elders.elderId) as ece\n" +
            "left join children on ece.childrenID=children.childrenId where adminId = ${adminId};")
    public List<RelationDto> selectRelation(Long adminId);

}
