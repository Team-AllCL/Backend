package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.UserDto;
import com.Cloudwave.Backend_AllCL.entity.User;
import com.Cloudwave.Backend_AllCL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
