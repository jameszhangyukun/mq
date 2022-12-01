package com.zyk.mq.common.support.hook;

import com.github.houbb.heaven.util.util.DateUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.zyk.mq.common.api.Destroyable;
import com.zyk.mq.common.support.invoke.IInvokeService;
import com.zyk.mq.common.support.status.IStatusManager;

/**
 * 默认hook实现
 */
public class DefaultShutdownHook extends AbstractShutdownHook {

    private static final Log logger = LogFactory.getLog(DefaultShutdownHook.class);

    private IInvokeService invokeService;

    private Destroyable destroyable;

    private IStatusManager statusManager;

    private long waitMillsForRemainRequest = 60 * 1000;

    public IInvokeService getInvokeService() {
        return invokeService;
    }

    public void setInvokeService(IInvokeService invokeService) {
        this.invokeService = invokeService;
    }

    public Destroyable getDestroyable() {
        return destroyable;
    }

    public void setDestroyable(Destroyable destroyable) {
        this.destroyable = destroyable;
    }

    public IStatusManager getStatusManager() {
        return statusManager;
    }

    public void setStatusManager(IStatusManager statusManager) {
        this.statusManager = statusManager;
    }

    public long getWaitMillsForRemainRequest() {
        return waitMillsForRemainRequest;
    }

    public void setWaitMillsForRemainRequest(long waitMillsForRemainRequest) {
        this.waitMillsForRemainRequest = waitMillsForRemainRequest;
    }

    /**
     * 1.设置status状态为等待关闭
     * 2.查看remainsRequest是否包含请求
     * 3.超时检测-可以不添加，如果难以关闭成功，直接强制关闭
     * 4.关闭所有线程池资源信息
     * 5.设置状态为成功关闭
     */
    @Override
    protected void doHook() {
        statusManager.status(false);
        logger.info("[Shutdown] set status to wait for shutdown.");

        long startMills = System.currentTimeMillis();

        while (invokeService.remainsRequest()) {
            long currentMills = System.currentTimeMillis();
            long costMills = currentMills - startMills;
            if (costMills >= waitMillsForRemainRequest) {
                logger.warn("[Shutdown] still remains request, but timeout, break.");
                break;
            }
            logger.debug("[Shutdown] still remains request, wait for a while.");
            DateUtil.sleep(100);
        }
        destroyable.destroyAll();
        statusManager.status(false);
        logger.info("[Shutdown] set status to shutdown success.");
    }
}
