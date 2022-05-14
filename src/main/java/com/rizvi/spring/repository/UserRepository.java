package com.rizvi.spring.repository;

import com.rizvi.spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
