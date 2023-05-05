package com.yiyang.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyang.manager.entity.Children;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChildrenMapper extends BaseMapper<Children> {
   /* @Select("select c.childrenId,c.name,password,c.sex,c.phone,c.address,c.createtime " +
            "from children as c,elders,elders_children_relation as ec " +
            "where c.childrenId=ec.childrenId and elders.elderId = ec.elderId and elders.adminId=${adminId};")*/
    @Select("select * from children ")
    public List<Children> childrenList(Long adminId);
}
