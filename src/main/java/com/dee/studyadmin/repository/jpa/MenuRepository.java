package com.dee.studyadmin.repository.jpa;

import com.dee.studyadmin.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByStatusOrderByUpdateTimeDesc(Pageable pageable, String status);

    List<Menu> findAllByOrderByParMenuId();

    List<Menu> findMenusByIdOrderByMenuIndex(Long id);

    List<Menu> findMenusByMenuTypeInOrderByParMenuIdAscMenuIndexAsc(List<String> menuTypes);
}

