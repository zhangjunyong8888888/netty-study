package com.study.netty.login.protocol.response;

import com.study.netty.login.protocol.Packet;
import com.study.netty.login.protocol.command.Command;
import lombok.Data;


@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;
    private String fromUserName;
    private String message;


    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    @Override
    public String toString() {
        return fromUserId + "-" + fromUserName + " -> :" + message;
    }
}
