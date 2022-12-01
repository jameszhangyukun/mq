package com.zyk.mq.common.util;

import com.alibaba.fastjson.JSON;
import com.zyk.mq.common.rpc.RpcMessageDto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class DelimiterUtils {

    private DelimiterUtils() {
    }

    public static final String DELIMITER = "~!@#$%^&*";

    public static final int LENGTH = 65535;

    public static final ByteBuf DELIMITER_BUF = Unpooled.copiedBuffer(DELIMITER.getBytes(StandardCharsets.UTF_8));

    public static ByteBuf getByteBuf(String text) {
        return Unpooled.copiedBuffer(text.getBytes(StandardCharsets.UTF_8));
    }

    public static ByteBuf getMessageDelimiterBuffer(RpcMessageDto rpcMessageDto) {
        String json = JSON.toJSONString(rpcMessageDto);
        String jsonDelimiter = json + DELIMITER;
        return Unpooled.copiedBuffer(jsonDelimiter.getBytes(StandardCharsets.UTF_8));
    }
}
