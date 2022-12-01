package com.zyk.mq.common.resp;

import com.zyk.mq.common.constant.MessageStatus;

public enum ConsumerStatus {
    SUCCESS(MessageStatus.CONSUMER_SUCCESS, "消费成功"),
    FAILED(MessageStatus.CONSUMER_FAILED, "消费失败"),
    CONSUMER_LATER(MessageStatus.CONSUMER_LATER, "稍后消费");

    private final String code;

    private final String desc;

    ConsumerStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
