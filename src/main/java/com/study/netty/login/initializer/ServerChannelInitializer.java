package com.study.netty.login.initializer;


import com.study.netty.login.handler.LoginRequestHandler;
import com.study.netty.login.handler.MessageRequestHandler;
import com.study.netty.login.protocol.PacketDecoder;
import com.study.netty.login.protocol.PacketEncoder;
import com.study.netty.login.protocol.Spliter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new Spliter());
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginRequestHandler());
        pipeline.addLast(new MessageRequestHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
