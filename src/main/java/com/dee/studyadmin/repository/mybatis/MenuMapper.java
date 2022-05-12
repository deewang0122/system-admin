package com.dee.studyadmin.repository.mybatis;

import com.dee.studyadmin.entity.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> findAll();

    List<Menu> findMenusByUser();
}
