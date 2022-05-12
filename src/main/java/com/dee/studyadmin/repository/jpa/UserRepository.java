package com.dee.studyadmin.repository.jpa;

import com.dee.studyadmin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByIdAsc();

    User findByLoginCodeAndPassword(String loginCode, String password);
}

