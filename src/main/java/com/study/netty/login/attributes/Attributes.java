package com.study.netty.login.attributes;

import com.study.netty.login.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    
    AttributeKey<Object> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
