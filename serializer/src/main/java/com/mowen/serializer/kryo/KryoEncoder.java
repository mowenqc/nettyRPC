package com.mowen.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.mowen.serializer.MessageEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.io.ByteArrayOutputStream;

/***
 * desc  : com.mowen.serializer.codec.kryo
 * author: mowen
 * create_time: 2019/5/31 9:00
 * project_name : rpc_parent
 */
@Sharable
public class KryoEncoder extends MessageEncoder {


    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        Kryo kryo = new Kryo();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Output output = new Output(outputStream);
        output.flush();
        output.close();
        kryo.writeClassAndObject(output, msg);
        byte[] bytes =  outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();
        out.writeBytes(bytes);
    }

    public static byte[] encodeObject(Object object){
        try {
            Kryo kryo = new Kryo();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Output output = new Output(outputStream);
            kryo.writeClassAndObject(output, object);
            output.flush();
            output.close();
            byte[] bytes =  outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] encodeMessage(Object object) {
        return encodeObject(object);
    }
}
