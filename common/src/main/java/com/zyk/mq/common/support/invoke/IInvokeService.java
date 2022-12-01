package com.zyk.mq.common.support.invoke;

import com.zyk.mq.common.rpc.RpcMessageDto;

/**
 * 服务调用接口
 */
public interface IInvokeService {
    /**
     * 添加请求信息
     */
    IInvokeService addRequest(final String seqId, final long timeoutMills);

    /**
     * 放入结果
     */
    IInvokeService addResponse(final String seqId, final RpcMessageDto rpcResponse);

    /**
     * 获取标志信息对应的结果
     */
    RpcMessageDto getResponse(final String seqId);

    /**
     * 是否依然包含请求待处理
     */
    boolean remainsRequest();
}
