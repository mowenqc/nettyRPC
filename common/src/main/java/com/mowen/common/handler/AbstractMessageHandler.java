package com.mowen.common.handler;

import com.mowen.common.aplication.ApplicationContext;
import com.mowen.common.message.ResMessage;
import com.mowen.common.util.NetUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.net.InetSocketAddress;
import org.apache.log4j.Logger;

/***
 * desc  : com.mowen.common.handler
 * author: mowen
 * create_time: 2019/6/11 15:00
 * project_name : nettyRPC_parent
 */
@Sharable
public abstract class AbstractMessageHandler extends ChannelInboundHandlerAdapter {

    protected Logger logger = Logger.getLogger(getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("创建连接");
        InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
        int port = socketAddress.getPort();
        String ip = socketAddress.getAddress().getHostAddress();
        String key = ip + ":" + port;
        ApplicationContext.addConnection(key, ctx.channel());
        logger.info("receive connection from " + key);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ResMessage){
            ResMessage resMessage = handlerMessage((ResMessage) msg);
            NetUtil.writeAndFlush(ctx, resMessage);
        }
        else {
            logger.info("接收到其他类消息，不予以处理");
        }
    }

    /**
     * 真正实现的处理类方法， 其他具体的实现操作类必须自我实现， 定义个模板方法
     * @param resMessage
     * @return
     */
    protected abstract ResMessage handlerMessage(ResMessage resMessage);
}
