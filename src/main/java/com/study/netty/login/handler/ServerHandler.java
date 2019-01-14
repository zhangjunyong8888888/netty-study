package com.study.netty.login.handler;

import com.study.netty.login.protocol.Packet;
import com.study.netty.login.protocol.PacketCodeC;
import com.study.netty.login.protocol.request.LoginRequestPacket;
import com.study.netty.login.protocol.request.MessageRequestPacket;
import com.study.netty.login.protocol.response.LoginResponsePacket;
import com.study.netty.login.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        ByteBuf responseBuffer = null;
        if (packet instanceof LoginRequestPacket) {
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            if (validLogin(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败！");
            }
            responseBuffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseBuffer);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(LocalDateTime.now() + "服务端收到消息：" + messageRequestPacket.getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("已收到您发送的消息" + messageRequestPacket.getMessage());
            responseBuffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseBuffer);
        }
    }

    private boolean validLogin(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
