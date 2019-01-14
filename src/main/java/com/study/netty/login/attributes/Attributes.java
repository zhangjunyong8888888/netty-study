package com.study.netty.login.attributes;

import io.netty.util.AttributeKey;

public interface Attributes {
    
    AttributeKey<Object> LOGIN = AttributeKey.newInstance("login");
}
