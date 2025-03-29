package com.Cloudwave.Backend_AllCL.service;

import com.Cloudwave.Backend_AllCL.repository.UserRepository;

import com.Cloudwave.Backend_AllCL.dto.UserDto;
import com.Cloudwave.Backend_AllCL.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        return userRepository.save(user);  // DB에 저장
    }
}
