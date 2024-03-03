package com.example.demo.repository;

import com.example.demo.domain.userHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserHistoryRepository extends JpaRepository <userHistory,String> {
    boolean existsByEmail(String email);
    // boolean isActive(String status);
//    boolean existsByPassword(String password);

// String userStatus(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    userHistory findByEmail(@Param("email") String email);

    @Query("SELECT u.status FROM User u WHERE u.email = :email")
    String findStatusByEmail(@Param("email") String email);
}
