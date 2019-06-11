package com.mowen.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.mowen.serializer.MessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.apache.log4j.Logger;

/***
 * desc  : com.mowen.serializer.codec.kryo
 * author: mowen
 * create_time: 2019/5/31 8:53
 * project_name : rpc_parent
 */
public class KryoDecoder extends MessageDecoder {
    private Logger logger = Logger.getLogger(getClass());
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("读取长度：" + in.readableBytes());
        if(in.readableBytes() < 4){
            return;
        }
        logger.info("bytebuf = " + in);
        int beginIndex = in.readerIndex();
        logger.info("beginIndex = " + beginIndex);
        int readInt = in.readInt();
        int readableBytes = in.readableBytes();
        logger.info("读取的字节数:readInt = " + readInt + ", remainInt = " + readableBytes);
        if(readableBytes < readInt){
            in.resetReaderIndex();
            return;
        }
        Kryo kryo = new Kryo();
        Input input = new Input(new ByteBufInputStream(in));
        Object object = kryo.readClassAndObject(input);

        if(object == null){
            return;
        }
        out.add(object);
    }
}
