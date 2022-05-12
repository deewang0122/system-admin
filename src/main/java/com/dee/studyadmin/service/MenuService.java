package com.dee.studyadmin.service;


import com.dee.studyadmin.entity.Menu;
import com.dee.studyadmin.repository.jpa.MenuRepository;
import com.dee.studyadmin.repository.mybatis.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class MenuService {

    private static final Long TOP_MENU_ID = 1L;

    @Autowired
    MenuRepository menuRepository;

    @Resource
    MenuMapper menuMapper;

    public Page<Menu> findAll(Pageable pageable, String status) {
        Page<Menu> page = menuRepository.findAllByStatusOrderByUpdateTimeDesc(pageable, status);

        return page;
    }

    public List<Menu> findMenusByUser() {
        List<Menu> list = menuMapper.findMenusByUser();

        return getMenuTree(TOP_MENU_ID, list);
    }

    public List<Menu> findAll() {
        List<Menu> list = menuMapper.findAll();

        return list;
    }

    public List<Menu> findMenusByMenuTypeInOrderByParMenuIdAscMenuIndexAsc(List<String> menuTypes) {
        List<Menu> list = menuRepository.findMenusByMenuTypeInOrderByParMenuIdAscMenuIndexAsc(menuTypes);

        return list;
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id).get();
    }


    public Menu save(Menu menu) {
        menu.setLevel(findById(menu.getParMenuId()).getLevel() + 1);
        return menuRepository.save(menu);
    }

    public Menu update(Menu menu) {
        Menu menu1 = menuRepository.getById(menu.getId());
        menu.setCreateTime(menu1.getCreateTime());
        menu.setCreateBy(menu1.getCreateBy());
        return menuRepository.saveAndFlush(menu);
    }

    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }


    private List<Menu> getMenuTree(Long parMenuId, List<Menu> menus) {
        List<Menu> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParMenuId() == parMenuId) {
                Menu child = new Menu();
                BeanUtils.copyProperties(menu, child);
                child.setChildMenus(getMenuTree(menu.getId(), menus));

                list.add(child);
            }
        }
        return list;
    }
}
