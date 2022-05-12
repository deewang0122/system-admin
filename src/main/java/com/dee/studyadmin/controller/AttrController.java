package com.dee.studyadmin.controller;

import com.dee.studyadmin.dto.Result;
import com.dee.studyadmin.entity.Attr;
import com.dee.studyadmin.service.AttrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/study/attr/")
@CrossOrigin
@Slf4j
public class AttrController extends BaseController {

    @Autowired
    AttrService attrService;

    @GetMapping("/findAll")
    public Result findAll() {
        List<Attr> list = attrService.findAll();
        return new Result<>(list, "success", 1);
    }

    @GetMapping("/findAttrAll")
    public Result findAll(String name, String type) {
        List<Attr> list = attrService.findAttrAll(name, type);
        return new Result<>(list, "success", 1);
    }

    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable() Long id ) {
        Attr attr = attrService.findById(id);
        return new Result<>(attr, "success", 1);
    }

    @PostMapping("/save")
    public Result save(@RequestBody @Valid Attr attr, HttpServletRequest request) {
        attr = attrService.save(attr);
        return new Result(attr, "save success", 1);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid Attr attr) {
        attr = attrService.update(attr);
        return new Result(attr, "update success", 1);
    }

    @PostMapping("/deleteById")
    public Result deleteById(Long id) {
        attrService.deleteById(id);
        return new Result("delete success", 1);
    }

}
