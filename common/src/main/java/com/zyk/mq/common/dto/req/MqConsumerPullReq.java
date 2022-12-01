package com.zyk.mq.common.dto.req;

public class MqConsumerPullReq {
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 拉取大小
     */
    private int size;
    /**
     * 标题名称
     */
    private String topicName;
    /**
     * 标签正则
     */
    private String tagRegex;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTagRegex() {
        return tagRegex;
    }

    public void setTagRegex(String tagRegex) {
        this.tagRegex = tagRegex;
    }

    @Override
    public String toString() {
        return "MqConsumerPullReq{" +
                "groupName='" + groupName + '\'' +
                ", size=" + size +
                ", topicName='" + topicName + '\'' +
                ", tagRegex='" + tagRegex + '\'' +
                '}';
    }
}
