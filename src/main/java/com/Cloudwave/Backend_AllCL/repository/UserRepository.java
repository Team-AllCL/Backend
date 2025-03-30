package com.Cloudwave.Backend_AllCL.repository;

import com.Cloudwave.Backend_AllCL.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
