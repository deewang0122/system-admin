package com.dee.studyadmin.service;


import com.dee.studyadmin.entity.Attr;
import com.dee.studyadmin.entity.Menu;
import com.dee.studyadmin.repository.jpa.AttrRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class AttrService {

    @Autowired
    AttrRepository attrRepository;

    public List<Attr> findAll() {
        return attrRepository.findAllByOrderByNameAscTypeAscKeyAsc();
    }

    public List<Attr> findAttrAll(String name, String type) {
        return attrRepository.findAllByNameAndTypeAndStatusOrderByKey(name, type, "10");
    }

    public Attr findById(Long id) {
        return attrRepository.findById(id).get();
    }


    public Attr save(Attr attr) {
        return attrRepository.save(attr);
    }

    public Attr update(Attr attr) {
        Attr attr1 = attrRepository.getById(attr.getId());
        attr.setCreateTime(attr1.getCreateTime());
        attr.setCreateBy(attr1.getCreateBy());
        return attrRepository.saveAndFlush(attr);
    }

    public void deleteById(Long id) {
        attrRepository.deleteById(id);
    }
}
