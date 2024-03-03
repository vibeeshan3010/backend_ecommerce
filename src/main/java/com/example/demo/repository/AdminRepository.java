package com.example.demo.repository;

import com.example.demo.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    boolean existsByEmail(String email);
    // boolean isActive(String status);
    boolean existsByPassword(String password);

// String userStatus(String email);

    @Query("SELECT u FROM Admin u WHERE u.email = :email")
    Admin findByEmail(@Param("email") String email);

    @Query("SELECT u.status FROM Admin u WHERE u.email = :email")
    String findStatusByEmail(@Param("email") String email);


}

