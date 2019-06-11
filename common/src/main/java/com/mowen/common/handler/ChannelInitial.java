package com.mowen.common.handler;

import com.mowen.serializer.kryo.KryoDecoder;
import com.mowen.serializer.kryo.KryoEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/***
 * desc  : com.mowen.server.handler
 * author: mowen
 * create_time: 2019/6/11 8:13
 * project_name : nettyRPC_parent
 */
public class ChannelInitial extends ChannelInitializer {

    private ChannelHandler channelHandler;
    public ChannelInitial(ChannelHandler channelHandler){
        this.channelHandler = channelHandler;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new KryoDecoder());
        pipeline.addLast(new KryoEncoder());
        pipeline.addLast(channelHandler);
    }
}
