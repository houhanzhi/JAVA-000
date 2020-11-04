package io.github.kimmking.gateway.netty4.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;


public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {

    private final String NIO = "nio";

    /**
     * 请求http://localhost:8888:--->网关(netty server)--->netty client--->http://localhost:8088/api/hello
     *                        <-----网关(netty server)<---(同一条channel)netty client    <---
     */
    // 保证Netty Server & Netty Client在同一channel中
    private ChannelHandlerContext ctx;

    private FullHttpRequest inbound;

    public NettyHttpClientOutboundHandler(ChannelHandlerContext ctx, FullHttpRequest inbound) {
        this.ctx = ctx;
        this.inbound = inbound;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("/api/hello");
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        request.headers().set(NIO,inbound.headers().get("nio"));
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            ByteBuf buf = response.content();
            String result = buf.toString(CharsetUtil.UTF_8);
            System.out.println("response -> " + result);
            ByteBuf byteBuf = Unpooled.copiedBuffer(result, CharsetUtil.UTF_8);
            DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            res.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            res.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            this.ctx.writeAndFlush(res);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}