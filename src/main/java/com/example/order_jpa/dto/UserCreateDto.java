package com.example.order_jpa.dto;

import com.example.order_jpa.entity.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserCreateDto {
    @NotBlank
    private String name;
    @NotNull //nullable false는 db설정, 이건 입력 바인딩 설정
    @NotBlank
    private String email;
    private String password;
    private String phone;
    private String address;
    private UserType userType;
}
