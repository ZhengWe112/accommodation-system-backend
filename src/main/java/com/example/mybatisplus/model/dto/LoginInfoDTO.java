package com.example.mybatisplus.model.dto;

import lombok.Data;

@Data
public class LoginInfoDTO {
    private String number;

    private String password;

    private Integer role;
}
