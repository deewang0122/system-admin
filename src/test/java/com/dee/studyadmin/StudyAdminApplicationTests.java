package com.dee.studyadmin;

import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.util.JWTUtils;
import com.dee.studyadmin.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import reactor.core.publisher.Mono;

@SpringBootTest
@Slf4j
class StudyAdminApplicationTests {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void test2() {
        User user = JWTUtils.getUserByToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi5rWL6K-VKyIsImxvZ2luQ29kZSI6ImNvbmRlMTIiLCJpZCI6MTcsImlhdCI6MTY1MjAwNTAwM30.pI_ap2gxcVNhvD5-SIXMr0BMY3qH4jc8y_ZzlyddgNA");

        log.info(JsonUtils.ObjToStr(user));
    }


}
