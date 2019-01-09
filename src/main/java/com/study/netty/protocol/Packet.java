package com.study.netty.protocol;

import lombok.Data;

@Data
public abstract class Packet {

    Byte LOGIN_REQUEST = 1;

    /**
     * 指令
     *
     * @return
     */
    public abstract Byte getCommand();
}
