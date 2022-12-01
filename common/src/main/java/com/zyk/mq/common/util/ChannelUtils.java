package com.zyk.mq.common.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class ChannelUtils {
    private ChannelUtils() {

    }

    public static String getChannelId(Channel channel) {
        return channel.id().asLongText();
    }

    public static String getChannelId(ChannelHandlerContext ctx) {
        return getChannelId(ctx.channel());
    }
}
