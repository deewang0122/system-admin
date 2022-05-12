package com.dee.studyadmin.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Login {
    @NotNull(message = "用户名不能为 null")
    @NotEmpty(message = "用户名长度需大于0")
    String loginCode;

    @NotNull(message = "密码不能为 null")
    @NotEmpty(message = "密码长度需大于0")
    String password;
}
