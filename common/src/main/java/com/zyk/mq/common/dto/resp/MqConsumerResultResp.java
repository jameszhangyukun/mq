package com.zyk.mq.common.dto.resp;

public class MqConsumerResultResp extends MqCommonResp {
    private String consumerStatus;


    public String getConsumerStatus() {
        return consumerStatus;
    }

    public void setConsumerStatus(String consumerStatus) {
        this.consumerStatus = consumerStatus;
    }
}
