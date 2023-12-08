package com.example.mydemo.repository;

import com.example.mydemo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByUsername(String username);
    // 還得多個U，要想辦法解決命名
//    User findByUsernameU(String username);
}
