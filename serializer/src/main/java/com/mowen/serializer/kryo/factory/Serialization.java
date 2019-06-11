package com.mowen.serializer.kryo.factory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/***
 * desc  : com.mowen.nettylearn.codec.kryo.serialize
 * author: mowen
 * create_time: 2019/5/31 15:56
 * project_name : rpc_parent
 */
public interface Serialization {
    void serialize(OutputStream out, Object message) throws IOException;
    Object deserialize(InputStream in) throws IOException;
}
