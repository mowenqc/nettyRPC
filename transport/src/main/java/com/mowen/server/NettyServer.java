package com.mowen.server;

import com.mowen.common.handler.ChannelInitial;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;

/***
 * desc  : com.mowen.server
 * author: mowen
 * create_time: 2019/6/10 16:01
 * project_name : nettyRPC_parent
 */
public class NettyServer {

    private Logger logger = Logger.getLogger(getClass());

    private int port;
    public NettyServer(int port){
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossEvent = new NioEventLoopGroup();
        EventLoopGroup workEven = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            ChannelInitial channelInitial = new ChannelInitial();
            bootstrap.group(bossEvent, workEven).channel(NioServerSocketChannel.class).option(
                ChannelOption.SO_BACKLOG, 1024).childHandler(channelInitial);
            ChannelFuture sync = bootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        finally {
            bossEvent.shutdownGracefully();
            workEven.shutdownGracefully();
        }
    }

}
