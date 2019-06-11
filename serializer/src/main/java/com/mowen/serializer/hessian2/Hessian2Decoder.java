package com.mowen.serializer.hessian2;

import com.caucho.hessian.io.Hessian2Input;
import com.mowen.serializer.MessageDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.io.ByteArrayInputStream;
import java.util.List;
import org.apache.log4j.Logger;

/***
 * desc  : com.mowen.nettylearn.codec.hessian2
 * author: mowen
 * create_time: 2019/5/30 19:15
 * project_name : mowen_parent
 */
public class Hessian2Decoder extends MessageDecoder {

    private Logger logger = Logger.getLogger(getClass());
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
        throws Exception {
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
        byte [] bytes = new byte[readInt];
        in.readBytes(bytes);
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        //Hessian的反序列化读取对象
        Hessian2Input hi = new Hessian2Input(is);
        Object object = hi.readObject();
        out.add(object);
    }
}
