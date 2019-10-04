package com.roll.wrench.web.biz.websocketserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.CharsetUtil;
import org.springframework.http.HttpHeaders;

/**
 * @author roll
 * created on 2019-09-30 17:25
 */
public class WebSocketIndexPageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String webSocketPath;

    public WebSocketIndexPageHandler(String webSocketPath) {
        this.webSocketPath = webSocketPath;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // handle a bad request
        if (!req.decoderResult().isSuccess()) {
            sendHeepResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, Unpooled.EMPTY_BUFFER));
            return;
        }

        if (!HttpMethod.GET.equals(req.method())) {
            sendHeepResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN, Unpooled.EMPTY_BUFFER));
            return;
        }

        if ("/".equals(req.uri()) || "/index.html".equals(req.uri())) {
            String webSocketLocation = getWebSocketLocation(ctx.pipeline(), req, webSocketPath);
            ByteBuf content = WebSocketServerIndexPage.getContent(webSocketLocation);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html, charset=utf-8");
            HttpUtil.setContentLength(response, content.readableBytes());
            sendHeepResponse(ctx, req, response);
        } else {
            sendHeepResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND, Unpooled.EMPTY_BUFFER));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    private static void sendHeepResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest, FullHttpResponse fullHttpResponse) {
        // Generate an error page if response code is not ok
        if (fullHttpResponse.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(fullHttpResponse.toString(), CharsetUtil.UTF_8);
            fullHttpResponse.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(fullHttpResponse, fullHttpResponse.content().readableBytes());
        }

        if (!HttpUtil.isKeepAlive(fullHttpRequest) || fullHttpResponse.status().code() != 200) {
            fullHttpResponse.headers().set(HttpHeaders.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
    }

    private static String getWebSocketLocation(ChannelPipeline channelPipeline, HttpRequest request, String path) {
        String potacal = "ws";
        if (channelPipeline.get(SslHandler.class) != null) {
            potacal = "wss";
        }
        return potacal + "://" + request.headers().get(HttpHeaderNames.HOST) + path;
    }
}
