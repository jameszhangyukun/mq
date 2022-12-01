package com.zyk.mq.common.dto.req;

public class MqConsumerUpdateStatusDto {

    private String messageId;

    private String messageStatus;

    private String consumerGroupName;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    @Override
    public String toString() {
        return "MqConsumerUpdateStatusDto{" +
                "messageId='" + messageId + '\'' +
                ", messageStatus='" + messageStatus + '\'' +
                ", consumerGroupName='" + consumerGroupName + '\'' +
                '}';
    }

}
