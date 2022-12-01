package com.zyk.mq.common.support.status;

/**
 * 状态管理
 */
public interface IStatusManager {
    /**
     * 获取状态编码
     */
    boolean status();

    /**
     * 设置状态编码
     */
    IStatusManager status(final boolean status);

    /**
     * 初始化失败
     */
    boolean initFailed();

    /**
     * 设置初始化失败
     */
    IStatusManager initFailed(final boolean failed);
}
