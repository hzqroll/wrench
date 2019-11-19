package com.roll.wrench.web.biz.websocketserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.security.cert.CertificateException;


/**
 * @author roll
 * created on 2019-09-26 21:18
 */
@Service
public class ChatServer implements InitializingBean {

    private static final boolean SSL = System.getProperty("ssl") != null;

    private static final int port = 9041;


    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(new ServerStart()).start();
    }

    class ServerStart implements Runnable {

        @Override
        public void run() {
            try {
                final SslContext sslContext;
                if (SSL) {
                    SelfSignedCertificate ssc = new SelfSignedCertificate();
                    sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
                } else {
                    sslContext = null;
                }

                EventLoopGroup bossGroup = new NioEventLoopGroup();
                EventLoopGroup workerGroup = new NioEventLoopGroup();

                try {
                    ServerBootstrap bootstrap = new ServerBootstrap();
                    bootstrap.group(bossGroup, workerGroup)
                            .channel(NioServerSocketChannel.class)
                            .handler(new LoggingHandler(LogLevel.DEBUG))
                            .childHandler(new WebSocketServerInitializer(sslContext));

                    Channel channel = bootstrap.bind(port).sync().channel();
                    channel.closeFuture().sync();
                } finally {
                    bossGroup.shutdownGracefully().sync();
                    workerGroup.shutdownGracefully().sync();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
