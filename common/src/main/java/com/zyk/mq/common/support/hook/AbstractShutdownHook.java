package com.zyk.mq.common.support.hook;

import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;

public abstract class AbstractShutdownHook implements RpcShutdownHook {

    /**
     * AbstractShutdownHook logger
     */
    private static final Log LOG = LogFactory.getLog(AbstractShutdownHook.class);

    public void hook() {
        LOG.info("[Shutdown Hook] start");
        this.doHook();
        LOG.info("[Shutdown Hook] end");
    }

    /**
     * 执行hook 操作
     */
    protected abstract void doHook();
}
