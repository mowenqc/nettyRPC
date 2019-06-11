package com.mowen.serializer.hessian2;

import com.mowen.serializer.CodecFactory;
import com.mowen.serializer.MessageDecoder;
import com.mowen.serializer.MessageEncoder;

/***
 * desc  : com.mowen.serializer.hessian2
 * author: mowen
 * create_time: 2019/6/11 15:47
 * project_name : nettyRPC_parent
 */
public class Hessian2CodecFactory implements CodecFactory {

    @Override
    public MessageEncoder encoder() {
        return new Hessian2Encoder();
    }

    @Override
    public MessageDecoder decoder() {
        return new Hessian2Decoder();
    }
}
