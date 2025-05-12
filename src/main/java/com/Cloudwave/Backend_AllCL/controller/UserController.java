package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.user.UserDto;
import com.Cloudwave.Backend_AllCL.entity.User;
import com.Cloudwave.Backend_AllCL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://dkgmp1k5bgam1.cloudfront.net")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerOrLogin(@RequestBody UserDto userDto) {
        User existingUser = userService.findByEmail(userDto.getEmail());

        if (existingUser != null) {
            // 이미 존재하는 유저일 경우 → 비밀번호 검증
            if (existingUser.getPassword().equals(userDto.getPassword())) {
                return existingUser; // 로그인 성공
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
            }
        }

        // 존재하지 않으면 새로 가입
        return userService.createUser(userDto);
    }
}
