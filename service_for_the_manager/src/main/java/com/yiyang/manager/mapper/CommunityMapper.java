package com.yiyang.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyang.manager.dto.TypeDto;
import com.yiyang.manager.entity.Community;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityMapper extends BaseMapper<Community> {
    @Select("select count(community.communityId) as number,type from community " +
            "left join (select communityId,type from elders left join request on elders.elderId = request.elderId) as ar " +
            "on ar.communityId = community.communityId " +
            "where community.communityId = (select communityId from admin where admin.adminId = ${adminId}) " +
            "group by type;")
    public List<TypeDto> countType(Long adminId);
}
