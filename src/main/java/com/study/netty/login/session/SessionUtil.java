package com.study.netty.login.session;

import com.study.netty.login.attributes.Attributes;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class SessionUtil {
    private static final Map<String, Channel> userSessions = new HashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userSessions.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userSessions.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userSessions.get(userId);
    }
}
