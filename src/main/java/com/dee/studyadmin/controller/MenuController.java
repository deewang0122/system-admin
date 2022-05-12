package com.dee.studyadmin.controller;

import com.dee.studyadmin.dto.Result;
import com.dee.studyadmin.entity.Menu;
import com.dee.studyadmin.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value = "/study/menu/")
@CrossOrigin
@Slf4j
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<Menu> list = menuService.findAll();
        return new Result<>(list, "success", 1);
    }

    @GetMapping("/findMenusByMenuTypeIn")
    public Result findMenusByMenuTypeInOrderByParMenuIdAscMenuIndexAsc(String menuTypes) {

        List<Menu> list = menuService.findMenusByMenuTypeInOrderByParMenuIdAscMenuIndexAsc(Arrays.asList(menuTypes.split(",")));

        return new Result<>(list, "success", 1);
    }

    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable() Long id ) {
        Menu menu = menuService.findById(id);
        return new Result<>(menu, "success", 1);
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid Menu menu, HttpServletRequest request) {
        setCreate(menu, request);
        menu = menuService.save(menu);
        return new Result(menu, "save success", 1);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid Menu menu, HttpServletRequest request) {
        setUpdate(menu, request);
        menu = menuService.update(menu);
        return new Result(menu, "update success", 1);
    }

    @PostMapping("/deleteById")
    public Result deleteById(Long id) {
        menuService.deleteById(id);
        return new Result("delete success", 1);
    }
}
