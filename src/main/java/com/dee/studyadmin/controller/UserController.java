package com.dee.studyadmin.controller;

import com.dee.studyadmin.dto.Result;
import com.dee.studyadmin.entity.Menu;
import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.service.MenuService;
import com.dee.studyadmin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/study/user/")
@CrossOrigin
@Slf4j
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<User> list = userService.findAll();
        return new Result<>(list, "success", 1);
    }


    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable() Long id ) {
        User user = userService.findById(id);
        return new Result<>(user, "success", 1);
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid User user, HttpServletRequest request) {
        setCreate(user, request);
        user = userService.save(user);
        return new Result(user, "save success", 1);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid User user, HttpServletRequest request) {
        setUpdate(user, request);
        user = userService.update(user);
        return new Result(user, "update success", 1);
    }

    @PostMapping("/deleteById")
    public Result deleteById(Long id) {
        userService.deleteById(id);
        return new Result("delete success", 1);
    }
}
