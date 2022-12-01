package com.zyk.mq.common.support.hook;

public final class ShutdownHooks {
    private ShutdownHooks() {

    }

    public static void rpcShutdownHook(final RpcShutdownHook rpcShutdownHook) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                rpcShutdownHook.hook();
            }
        });
    }
}
