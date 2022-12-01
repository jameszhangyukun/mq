package com.zyk.mq.common.rpc;

import io.netty.channel.ChannelFuture;

public class RpcChannelFuture extends RpcAddress {
    private ChannelFuture channelFuture;

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }
}
