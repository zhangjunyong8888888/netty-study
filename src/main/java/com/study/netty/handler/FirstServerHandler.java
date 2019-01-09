package com.study.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class FirstServerHandler  extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDateTime.now()+":服务端读到数据！"+byteBuf.toString(Charset.forName("UTF-8")));
        System.out.println(LocalDateTime.now() + ": 服务端写出数据");
        ByteBuf outBuf = getOutByteBuf(ctx);
        ctx.writeAndFlush(outBuf);
    }

    private ByteBuf getOutByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] bytes = "你好，客户端，我是服务端！".getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(bytes);
        return  byteBuf;
    }


}
