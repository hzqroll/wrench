package com.roll.wrench.web.biz.websocket1.test;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author roll
 * created on 2019-11-27 19:47
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("http-codec",
                new HttpServerCodec());
        pipeline.addLast("aggregator",
                new HttpObjectAggregator(65536));
        ch.pipeline().addLast("http-chunked",
                new ChunkedWriteHandler());
        pipeline.addLast("handler",
                new TestWebSocketServerHandler());
    }
}
