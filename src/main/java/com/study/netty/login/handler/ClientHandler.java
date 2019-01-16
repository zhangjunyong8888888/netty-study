package com.study.netty.login.handler;

import com.study.netty.login.protocol.Packet;
import com.study.netty.login.protocol.PacketCodeC;
import com.study.netty.login.protocol.request.LoginRequestPacket;
import com.study.netty.login.protocol.response.LoginResponsePacket;
import com.study.netty.login.protocol.response.MessageResponsePacket;
import com.study.netty.login.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(LocalDateTime.now() + ": 客户端开始登录");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        //loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("user_name_8888888");
        loginRequestPacket.setPassword("user_pwd_123456");
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println("登录成功！");
            } else {
                System.err.println("登录失败！失败原因为：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(LocalDateTime.now() + "收到服务端回复的消息：" + messageResponsePacket.getMessage());
        }
    }
}
