package com.roll.wrench.web.biz.websocketserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author roll
 * created on 2019-09-27 08:24
 */
public class ChatMessageHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端消息：" + s);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接：" + ctx.channel().localAddress());
        super.channelRegistered(ctx);
    }
}
