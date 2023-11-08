package com.example.order_jpa.dto;

import com.example.order_jpa.entity.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private UserType userType;
}
