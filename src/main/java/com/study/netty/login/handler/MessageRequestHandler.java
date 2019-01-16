package com.study.netty.login.handler;

import com.study.netty.login.protocol.request.MessageRequestPacket;
import com.study.netty.login.protocol.response.MessageResponsePacket;
import com.study.netty.login.session.Session;
import com.study.netty.login.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        Session session = SessionUtil.getSession(ctx.channel());
        MessageResponsePacket response = new MessageResponsePacket();
        response.setMessage(messageRequestPacket.getMessage());
        response.setFromUserId(session.getUserId());
        response.setFromUserName(session.getUserName());
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(response);
        } else {
            System.out.println("对方不在线发送失败");
            ctx.channel().writeAndFlush("对方不在线发送失败");
        }
    }
}
