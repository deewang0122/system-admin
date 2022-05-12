package com.dee.studyadmin.controller;

import com.dee.studyadmin.dto.Login;
import com.dee.studyadmin.dto.Result;
import com.dee.studyadmin.entity.Menu;
import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.filter.RedisSession;
import com.dee.studyadmin.service.LoginService;
import com.dee.studyadmin.service.MenuService;
import com.dee.studyadmin.util.JWTUtils;
import com.dee.studyadmin.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@Slf4j
public class LoginController {

    @Autowired
    MenuService menuService;

    @Autowired
    LoginService loginService;

    @Autowired
    RedisSession redisSession;


    @PostMapping("/login")
    public Result login(@RequestBody @Valid Login login) {
        Result result = loginService.login(login);
        return result;
    }

    @GetMapping("/study/home/findMenusByUser")
    public Result findAll(){
        List<Menu> list = menuService.findMenusByUser();
        return new Result<>(list, "success", 1);
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        Boolean b = loginService.logout(request.getHeader("Authorization"));
        return new Result<>(b, "success", 1);
    }
}
