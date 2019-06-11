package com.mowen.serializer;

import io.netty.handler.codec.MessageToByteEncoder;

/***
 * desc  : com.mowen.serializer
 * author: mowen
 * create_time: 2019/6/11 15:43
 * project_name : nettyRPC_parent
 */
public abstract class MessageEncoder extends MessageToByteEncoder {

    /**
     * 获取二进制对象数据
     * @param object
     * @return
     */
    public abstract byte[] encodeMessage(Object object);
}
