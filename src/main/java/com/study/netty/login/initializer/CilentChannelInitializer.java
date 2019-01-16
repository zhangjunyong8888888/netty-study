package com.study.netty.login.initializer;

import com.study.netty.login.handler.LoginResponseHandler;
import com.study.netty.login.handler.MessageResponseHandler;
import com.study.netty.login.handler.StringMessageResponseHandler;
import com.study.netty.login.protocol.PacketDecoder;
import com.study.netty.login.protocol.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class CilentChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginResponseHandler());
        pipeline.addLast(new MessageResponseHandler());
        pipeline.addLast(new StringMessageResponseHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
