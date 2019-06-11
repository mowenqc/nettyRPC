package com.mowen.serializer.hessian2;

import com.caucho.hessian.io.Hessian2Output;
import com.mowen.serializer.MessageEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/***
 * desc  : com.mowen.nettylearn.codec.hessian2
 * author: mowen
 * create_time: 2019/5/30 19:14
 * project_name : mowen_parent
 */
public class Hessian2Encoder extends MessageEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        byte[] bytes = getObjectBytes(msg);
        if(bytes == null || bytes.length == 0){
            return;
        }
        int length = bytes.length;
        out.writeInt(length);
        out.writeBytes(bytes);
    }

    private static byte[] getObjectBytes(Object msg) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //Hessian的序列化输出
        Hessian2Output ho = new Hessian2Output(os);
        ho.writeObject(msg);
        ho.flush();
        return os.toByteArray();
    }

    public static byte[] encodeObject(Object object){
        try {
            return getObjectBytes(object);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public byte[] encodeMessage(Object object) {
        return encodeObject(object);
    }
}
