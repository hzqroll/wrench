package com.roll.wrench.web.biz.websocket;

import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

/**
 * @author roll
 * created on 2019-10-02 20:32
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker webSocketClientHandshaker;

    private ChannelPromise handshakerFuture;

    public WebSocketClientHandler(WebSocketClientHandshaker webSocketClientHandshaker) {
        this.webSocketClientHandshaker = webSocketClientHandshaker;
    }

    public ChannelFuture handshakeFuture() {
        return handshakerFuture;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakerFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        webSocketClientHandshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("WebSocket Client disconnected!");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        if (!webSocketClientHandshaker.isHandshakeComplete()) {
            try {
                webSocketClientHandshaker.finishHandshake(channel, (FullHttpResponse) msg);
                System.out.println("websocket client connected!");
                handshakerFuture.setSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                handshakerFuture.setFailure(e);
            }
            return;
        }

        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException(
                    "Unexpected FullHttpResponse (getStatus=" + response.status() +
                            ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        }

        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) frame;
            System.out.println("websocket client received message: " + textWebSocketFrame.text());
        } else if (frame instanceof PongWebSocketFrame) {
            System.out.println("websocket client received pong");
        } else if (frame instanceof CloseWebSocketFrame) {
            System.out.println("Websocket Client received closing");
            channel.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        if (!handshakerFuture.isDone()) {
            handshakerFuture.setFailure(cause);
        }
        ctx.close();
    }
}
