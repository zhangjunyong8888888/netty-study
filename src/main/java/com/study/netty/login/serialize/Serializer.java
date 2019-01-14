package com.study.netty.login.serialize;


import com.study.netty.login.serialize.impl.JSONSerializer;

public interface Serializer {
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);


    Serializer DEFAULT = new JSONSerializer();
}
