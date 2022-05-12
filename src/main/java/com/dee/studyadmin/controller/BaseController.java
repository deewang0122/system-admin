package com.dee.studyadmin.controller;

import com.dee.studyadmin.entity.BaseEntity;
import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.util.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class BaseController {

    public BaseEntity setCreate(BaseEntity baseEntity, HttpServletRequest request) {
        User user = JWTUtils.getUserByToken(request.getHeader("Authorization"));

        baseEntity.setCreateBy(user.getId());
        baseEntity.setCreateTime(new Date());
        return baseEntity;
    }

    public BaseEntity setUpdate(BaseEntity commonEntity, HttpServletRequest request) {
        User user = JWTUtils.getUserByToken(request.getHeader("Authorization"));

        commonEntity.setUpdateBy(user.getId());
        commonEntity.setUpdateTime(new Date());
        return commonEntity;
    }
}
