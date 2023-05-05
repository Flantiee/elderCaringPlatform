package com.yiyang.manager.dto;

import com.yiyang.manager.entity.Children;
import com.yiyang.manager.entity.Elders;
import lombok.Data;

import java.util.List;

@Data
public class AvailableDto {
    private List<Elders> elders;

    private List<Children> children;
}
