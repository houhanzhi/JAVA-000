package io.github.kimmking.gateway.netty4.outbound;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.net.URISyntaxException;

/**
 * @author 来自猩猩的攻城狮hhz
 * @date 2020-11-03 14:44
 */
public class NettyHttpClientFilter implements HttpRequestFilter {

    private final String NIO = "nio";

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) throws URISyntaxException {
        HttpHeaders headers = fullRequest.headers();
        headers.set(NIO, "houhanzhi");
    }
}
