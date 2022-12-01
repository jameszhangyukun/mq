package com.zyk.mq.common.constant;

public class MessageStatus {

    private MessageStatus() {
    }

    /**
     * 待消费
     */
    public static final String WAIT_CONSUMER = "W";
    /**
     * 推送给消费端处理中
     */
    public static final String TO_CONSUMER_PROCESS = "TCP";
    /**
     * 推送给消费端成功
     */
    public static final String TO_CONSUMER_SUCCESS = "TCS";
    /**
     * 推送消费端失败
     */
    public static final String TO_CONSUMER_FAILED = "TCF";
    /**
     * 消费完成
     */
    public static final String CONSUMER_SUCCESS = "CS";
    /**
     * 消费失败
     */
    public static final String CONSUMER_FAILED = "CF";
    /**
     * 稍后消费
     */
    public static final String CONSUMER_LATER = "CL";

}
