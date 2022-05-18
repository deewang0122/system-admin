package com.dee.studyadmin.service;


import com.dee.studyadmin.constant.UserConstant;
import com.dee.studyadmin.entity.User;
import com.dee.studyadmin.repository.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {

        return userRepository.findAllByOrderByUpdateTimeAscIdAsc();
    }

    public User findByLoginCodeAndPassword(String loginCode, String password) {
        return userRepository.findByLoginCodeAndPassword(loginCode, password);
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        User user1 = userRepository.getById(user.getId());
        user.setPassword(user1.getPassword());
        user.setCreateBy(user1.getCreateBy());
        user.setCreateTime(user1.getCreateTime());
        return userRepository.saveAndFlush(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }



}
