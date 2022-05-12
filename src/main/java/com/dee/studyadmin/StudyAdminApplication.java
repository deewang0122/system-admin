package com.dee.studyadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@MapperScan(basePackages =  "com.dee.studyadmin.repository.mybatis")
@ComponentScans(value = {@ComponentScan(value = "com.dee.studyadmin.filter"),
        @ComponentScan(value = "com.dee.studyadmin.util")})
public class StudyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyAdminApplication.class, args);
    }

}
