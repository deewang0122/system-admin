package com.dee.studyadmin.repository.jpa;

import com.dee.studyadmin.entity.Attr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttrRepository extends JpaRepository<Attr, Long> {

    List<Attr> findAllByNameAndTypeAndStatusOrderByKey(String name, String type, String status);

    List<Attr> findAllByOrderByNameAscTypeAscKeyAsc();
}

