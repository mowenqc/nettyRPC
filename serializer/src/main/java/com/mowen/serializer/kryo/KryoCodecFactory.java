package com.mowen.serializer.kryo;

import com.mowen.serializer.CodecFactory;
import com.mowen.serializer.MessageDecoder;
import com.mowen.serializer.MessageEncoder;

/***
 * desc  : com.mowen.serializer.kryo
 * author: mowen
 * create_time: 2019/6/11 15:46
 * project_name : nettyRPC_parent
 */
public class KryoCodecFactory implements CodecFactory {

    @Override
    public MessageEncoder encoder() {
        return new KryoEncoder();
    }

    @Override
    public MessageDecoder decoder() {
        return new KryoDecoder();
    }
}
