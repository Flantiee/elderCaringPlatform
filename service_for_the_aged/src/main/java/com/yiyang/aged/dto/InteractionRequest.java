package com.yiyang.aged.dto;

import lombok.Data;

@Data
public class InteractionRequest {

    private int interactionId;

    private String initiator;

    private Long initiatorId;

    private int receiverAccepted;

    private String initiatorAddress;

}

