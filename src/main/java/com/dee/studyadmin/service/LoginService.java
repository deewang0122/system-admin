package com.dee.studyadmin.service;


import com.dee.studyadmin.dto.Login;
import com.dee.studyadmin.dto.Result;
import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.filter.RedisSession;
import com.dee.studyadmin.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class LoginService {

    @Autowired
    UserService userService;

    @Autowired
    RedisSession redisSession;



    public Result login(Login login) {
        User user = userService.findByLoginCodeAndPassword(login.getLoginCode(), login.getPassword());

        Result<String> result = validate(login, user);
        if (result.getCode().intValue() != 200) {
            return result;
        }

        String token = JWTUtils.createToken(user);
        redisSession.set(token);
        //保存登陆日志
        //todo...
        return new Result(token, "login success", 1);
    }

    public boolean logout(String token) {
        Boolean b = redisSession.remove(token);
        //保存登陆日志
        //todo...
        return b;
    }

    private Result validate(Login login, User user) {
        if (Objects.isNull(user)) {
            return new Result( "", "登陆失败！当前用户不存在", HttpStatus.BAD_REQUEST.value());
        }

        // todo... 密码加密处理
        if (!login.getPassword().equals(user.getPassword())) {
            return new Result( "", "登陆失败！输入密码错误", HttpStatus.BAD_REQUEST.value());
        }

        if (!user.getStatus().equals("10")) {
            return new Result("", "登陆失败！用户已冻结/失效", HttpStatus.BAD_REQUEST.value());
        }

        return new Result("", "登陆成功！", HttpStatus.OK.value());
    }


}
