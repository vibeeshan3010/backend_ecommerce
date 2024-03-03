package com.example.demo.domain;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "lastname")
    private String lastname;
    @Column(unique = true)
    private String email;

    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//
//    private LocalDateTime loggedInTime;
//    private LocalDateTime loggedOutTime;

//    private String role;
    private String password;
//    private String gender;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




//
//    public LocalDateTime getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDateTime endDate) {
//        this.endDate = endDate;
//    }
//
//    public LocalDateTime getLoggedInTime() {
//        return loggedInTime;
//    }
//
//    public void setLoggedInTime(LocalDateTime loggedInTime) {
//        this.loggedInTime = loggedInTime;
//    }
//
//    public LocalDateTime getLoggedOutTime() {
//        return loggedOutTime;
//    }
//
//    public void setLoggedOutTime(LocalDateTime loggedOutTime) {
//        this.loggedOutTime = loggedOutTime;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getGender() {
//        return gender;
//    }

//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String phone;
    private String address;

    // Getters and setters
}