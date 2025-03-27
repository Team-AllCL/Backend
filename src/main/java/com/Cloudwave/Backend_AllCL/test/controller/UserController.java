package com.Cloudwave.Backend_AllCL.test.controller;

import com.Cloudwave.Backend_AllCL.test.entity.User;
import com.Cloudwave.Backend_AllCL.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "success";
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
