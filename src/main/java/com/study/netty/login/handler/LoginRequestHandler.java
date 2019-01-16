package com.study.netty.login.handler;

import com.study.netty.login.protocol.request.LoginRequestPacket;
import com.study.netty.login.protocol.response.LoginResponsePacket;
import com.study.netty.login.session.Session;
import com.study.netty.login.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket response = new LoginResponsePacket();
        response.setSuccess(true);
        Channel channel = channelHandlerContext.channel();
        String userId = channel.id().asShortText();
        response.setUserId(userId);
        SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channel);
        channel.writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
