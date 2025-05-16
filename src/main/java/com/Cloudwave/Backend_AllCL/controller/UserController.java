package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.user.UserDto;
import com.Cloudwave.Backend_AllCL.entity.User;
import com.Cloudwave.Backend_AllCL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@CrossOrigin(origins = "http://dkgmp1k5bgam1.cloudfront.net")
@CrossOrigin(origins = {
        "http://allcle-order.click",
        "https://allcle-order.click"
})


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
            System.out.println(">>> 입력된 비밀번호: [" + userDto.getPassword() + "]");
            System.out.println(">>> DB 저장된 비밀번호: [" + existingUser.getPassword() + "]");

            if (userDto.getPassword().equals(existingUser.getPassword())) {
                System.out.println(">>> 로그인 성공");
                return existingUser;
            } else {
                System.out.println(">>> 비밀번호 불일치");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
            }
        }

        System.out.println(">>> 신규 유저로 등록");
        return userService.createUser(userDto);
    }

}
