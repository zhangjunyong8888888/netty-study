package com.study.netty.login.handler;

import com.study.netty.login.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.isSuccess()) {
            System.out.println("登陆成功,您的用户ID为：" + loginResponsePacket.getUserId());
        } else {
            System.out.println("登陆失败！失败原因为：" + loginResponsePacket.getReason());
        }
    }
}
