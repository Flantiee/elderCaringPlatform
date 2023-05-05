package com.yiyang.aged.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("neighbor_interaction")
public class NeighborInteraction implements Serializable {

    @TableId
    private int interactionID;

    private String initiator;

    private Long initiatorId;

    private String receiver;

    private Long receiverId;

    private int receiverAccepted;

}
