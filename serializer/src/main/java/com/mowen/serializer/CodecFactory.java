package com.mowen.serializer;

/***
 * desc  : com.mowen.serializer
 * author: mowen
 * create_time: 2019/6/11 15:41
 * project_name : nettyRPC_parent
 */
public interface CodecFactory {

    /**
     * 创建编码器
     * @return
     */
    MessageEncoder encoder();

    /**
     * 创建解码器
     * @return
     */
    MessageDecoder decoder();
}
