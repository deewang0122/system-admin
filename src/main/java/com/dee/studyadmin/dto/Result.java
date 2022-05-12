package com.dee.studyadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;

    private T data;

    private String message;

    public Result(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public Result(T object, String message, Integer code) {
        this.data = object;
        this.message = message;
        this.code = code;
    }
}
