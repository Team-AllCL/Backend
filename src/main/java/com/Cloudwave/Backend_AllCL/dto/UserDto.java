package com.Cloudwave.Backend_AllCL.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private String email;
    private String password;

    public UserDto() {}

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
