package com.study.netty.login.protocol.response;

import com.study.netty.login.protocol.Packet;
import com.study.netty.login.protocol.command.Command;
import lombok.Data;


@Data
public class LoginResponsePacket extends Packet {

    private String userId;
    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
