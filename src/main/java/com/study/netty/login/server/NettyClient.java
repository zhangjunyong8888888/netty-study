package com.study.netty.login.server;

import com.study.netty.login.initializer.CilentChannelInitializer;
import com.study.netty.login.protocol.request.LoginRequestPacket;
import com.study.netty.login.protocol.request.MessageRequestPacket;
import com.study.netty.login.session.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {


    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new CilentChannelInitializer());
        //连接NettyServer
        connect(bootstrap, "127.0.0.1", 8089);
    }

    /**
     * 链接服务器
     *
     * @param bootstrap
     * @param host
     * @param port
     */
    private static void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                Channel channel = ((ChannelFuture) future).channel();
                // 连接成功之后，启动控制台线程
                startConsoleThread(channel);
            } else {
                System.err.println("连接失败!");
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    System.out.println("请输入您好有的ID: ");
                    String toUseId = sc.nextLine();
                    System.out.println("请输入您要发送的消息: ");
                    String message = sc.nextLine();
                    channel.writeAndFlush(new MessageRequestPacket(toUseId, message));
                } else {
                    System.out.println("请输入用户名: ");
                    String userName = sc.nextLine();
                    System.out.println("请输入密码: ");
                    String password = sc.nextLine();
                    loginRequestPacket.setUsername(userName);
                    loginRequestPacket.setPassword(password);
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                }
            }

        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}