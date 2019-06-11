package com.mowen.common.util;

import com.mowen.common.aplication.ApplicationContext;
import com.mowen.common.message.ResMessage;
import com.mowen.serializer.MessageEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelProgressivePromise;

/***
 * desc  : com.mowen.common.util
 * author: mowen
 * create_time: 2019/6/11 15:35
 * project_name : nettyRPC_parent
 */
public class NetUtil {

    public static void writeAndFlush(Channel channel, ResMessage resMessage){
        ByteBuf buffer = getByteBuf(resMessage);
        channel.writeAndFlush(buffer);
    }

    private static ByteBuf getByteBuf(ResMessage resMessage) {
        MessageEncoder encoder = ApplicationContext.findCodecFactory().encoder();
        byte[] bytes = encoder.encodeMessage(resMessage);
        int length = bytes.length;
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(length);
        buffer.writeBytes(bytes);
        return buffer;
    }

    public static void writeAndFlush(ChannelHandlerContext ctx, ResMessage message){
        ByteBuf buffer = getByteBuf(message);
        ctx.writeAndFlush(buffer);
    }

}
