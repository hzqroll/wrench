package com.roll.wrench.web.biz.websocket1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * @author roll
 * created on 2019-10-03 18:32
 */
@Service(value = "chatServer1")
public class ChatServer implements InitializingBean {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    private final EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    private ChannelInitializer<Channel> createInitializer(ChannelGroup channels) {
        return new ChatServerInitializer(channels);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        int port = 9991;
        new Thread(new ServerStart(new InetSocketAddress(port))).start();
    }

    public class ServerStart implements Runnable {
        private InetSocketAddress address;

        public ServerStart(InetSocketAddress address) {
            this.address = address;
        }

        @Override
        public void run() {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(createInitializer(channelGroup));

            ChannelFuture future = null;
            try {
                future = serverBootstrap.bind(address).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel = future.channel();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (channel != null) {
                        channel.close().sync();
                    }
                    channelGroup.close().sync();
                    group.shutdownGracefully().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));

            try {
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
