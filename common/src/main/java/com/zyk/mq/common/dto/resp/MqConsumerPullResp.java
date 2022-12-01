package com.zyk.mq.common.dto.resp;

import com.zyk.mq.common.dto.req.MqMessage;

import java.util.List;

public class MqConsumerPullResp extends MqCommonResp {

    private List<MqMessage> list;

    public List<MqMessage> getList() {
        return list;
    }

    public void setList(List<MqMessage> list) {
        this.list = list;
    }
}
