package com.study.netty.login.protocol;

import lombok.Data;

@Data
public abstract class Packet {

    Byte LOGIN_REQUEST = 1;
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     *
     * @return
     */
    public abstract Byte getCommand();
}
