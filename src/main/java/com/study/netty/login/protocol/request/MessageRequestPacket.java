package com.study.netty.login.protocol.request;

import com.study.netty.login.protocol.Packet;
import com.study.netty.login.protocol.command.Command;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}