package com.Cloudwave.Backend_AllCL.repository;

import com.Cloudwave.Backend_AllCL.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
