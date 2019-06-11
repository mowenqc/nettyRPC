package com.mowen.server.handler;

import com.mowen.common.aplication.ApplicationContext;
import com.mowen.common.handler.AbstractMessageHandler;
import com.mowen.common.message.ResMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.apache.log4j.Logger;

/***
 * desc  : com.mowen.server.handler
 * author: mowen
 * create_time: 2019/6/11 8:01
 * project_name : nettyRPC_parent
 */
public class MessageHandler extends AbstractMessageHandler {
    @Override
    protected ResMessage handlerMessage(ResMessage resMessage) {
        return null;
    }
}
