package com.zyk.mq.common.support.invoke.impl;

import com.alibaba.fastjson.JSON;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.zyk.mq.common.resp.MqCommonRespCode;
import com.zyk.mq.common.resp.MqException;
import com.zyk.mq.common.rpc.RpcMessageDto;
import com.zyk.mq.common.support.invoke.IInvokeService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 超时检测线程
 */
public class InvokeService implements IInvokeService {

    private static final Log logger = LogFactory.getLog(InvokeService.class);


    /**
     * 请求序列号 map
     * （1）这里后期如果要添加超时检测，可以添加对应的超时时间。
     * 可以把这里调整为 map
     * <p>
     * key: seqId 唯一标识一个请求
     * value: 存入该请求最长的有效时间。用于定时删除和超时判断。
     */
    private final ConcurrentHashMap<String, Long> requestMap;

    /**
     * 响应结果
     */
    private final ConcurrentHashMap<String, RpcMessageDto> responseMap;

    public InvokeService() {
        requestMap = new ConcurrentHashMap<>();
        responseMap = new ConcurrentHashMap<>();

        final Runnable timeoutThread = new TimeoutCheckThread(requestMap, responseMap);
        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(timeoutThread, 60, 60, TimeUnit.SECONDS);
    }

    @Override
    public IInvokeService addRequest(String seqId, long timeoutMills) {
        logger.debug("[Invoke] start add request for seqId: {}, timeoutMills: {}", seqId,
                timeoutMills);

        final long expireTime = System.currentTimeMillis() + timeoutMills;
        requestMap.putIfAbsent(seqId, expireTime);
        return this;
    }

    @Override
    public IInvokeService addResponse(String seqId, RpcMessageDto rpcResponse) {
        Long expireTime = this.requestMap.get(seqId);
        // 如果为空，结果已经超时，被定时job移除之后，响应结果才过来，直接忽略
        if (ObjectUtil.isNull(expireTime)) {
            return this;
        }

        // 2. 判断是否超时
        if (System.currentTimeMillis() > expireTime) {
            logger.debug("[Invoke] seqId:{} 信息已超时，直接返回超时结果。", seqId);
            rpcResponse = RpcMessageDto.timeout();
        }
        // 在放入之前可以添加判断，如果seqId必须处理请求集合中，才允许放入，或者直接忽略丢弃
        // 通知所有等待方
        responseMap.putIfAbsent(seqId, rpcResponse);
        logger.debug("[Invoke] 获取结果信息，seqId: {}, rpcResponse: {}", seqId, JSON.toJSON(rpcResponse));
        logger.debug("[Invoke] seqId:{} 信息已经放入，通知所有等待方", seqId);

        requestMap.remove(seqId);
        logger.debug("[Invoke] seqId:{} remove from request map", seqId);

        synchronized (this) {
            this.notifyAll();
            logger.debug("[Invoke] {} notifyAll()", seqId);
        }
        return this;
    }

    @Override
    public RpcMessageDto getResponse(String seqId) {
        try {
            RpcMessageDto rpcResponse = this.responseMap.get(seqId);
            if (ObjectUtil.isNotNull(rpcResponse)) {
                logger.debug("[Invoke] seq {} 对应结果已经获取：{}", seqId, rpcResponse);
                return rpcResponse;
            }
            while (rpcResponse == null) {
                logger.debug("[Invoke] seq {} 对应结果为空，进入等待", seqId);
                synchronized (this) {
                    this.wait();
                }

                logger.debug("[Invoke] {} wait has notified!", seqId);
                rpcResponse = this.responseMap.get(seqId);
                logger.debug("[Invoke] seq {} 对应结果已经获取: {}", seqId, rpcResponse);
            }
            return rpcResponse;
        } catch (InterruptedException e) {
            logger.error("获取响应异常", e);
            throw new MqException(MqCommonRespCode.RPC_GET_RESP_FAILED);
        }
    }

    @Override
    public boolean remainsRequest() {
        return this.requestMap.size() > 0;
    }
}