package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
 boolean existsByEmail(String email);
// boolean isActive(String status);
 boolean existsByPassword(String password);

// String userStatus(String email);

 @Query("SELECT u FROM User u WHERE u.email = :email")
 User findByEmail(@Param("email") String email);

 @Query("SELECT u.status FROM User u WHERE u.email = :email")
 String findStatusByEmail(@Param("email") String email);


}

