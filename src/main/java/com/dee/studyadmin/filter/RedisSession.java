package com.dee.studyadmin.filter;

import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.util.JWTUtils;
import com.dee.studyadmin.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class RedisSession {
    private static final String SESSION_PREFIX = "LOGIN_SESSION_";

    @Value("${session.expire_time}")
    private String expire_time;

    @Autowired
    RedisUtils redisUtils;

    public void set(String token) {
        String key = getKey(token);
        redisUtils.set(key, token, Long.valueOf(expire_time));
    }

    public void refresh(String token) {
        String key = getKey(token);
        redisUtils.refresh(key, token, Long.valueOf(expire_time));
    }

    public Boolean remove(String token) {
        Boolean delete = redisUtils.delete(getKey(token));
        return delete;
    }

    public Boolean exist(String token) {
        return redisUtils.hasKey(getKey(token));
    }

    public String get(String token) {
        return redisUtils.get(getKey(token));
    }

    public Integer countLoginOnLine() {
        Set<String> keys = null;//redisUtils.findKeysByPattern(SESSION_PREFIX);
        return keys.size();
    }

    private String getKey(String param) {
        User user = JWTUtils.getUserByToken(param);
        return SESSION_PREFIX + user.getId() + "_" + user.getLoginCode() + "_" + user.getName();
    }


}
