package com.Cloudwave.Backend_AllCL.test.repository;

import com.Cloudwave.Backend_AllCL.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
