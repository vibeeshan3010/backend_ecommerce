package com.example.demo.service;

import com.example.demo.domain.Admin;
import com.example.demo.domain.AdminLogin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> listAll() {
        return adminRepository.findAll();
    }

    @Transactional
    public Admin saveAdmin(Admin admin) {

        if (!adminRepository.existsByEmail(admin.getEmail())) {

            System.out.println("New Email" + admin.getEmail());
            String firstname=admin.getFirstname();
            System.out.println("Admin First Name "+firstname);
            String lastname=admin.getLastname();
            System.out.println("Admin Last Name "+lastname);
            String email= admin.getEmail();
            String phone=admin.getPhone();
            System.out.println("Email"+ email);
//
            String encryptedpassword= EncryptionUtils.encrypt(admin.getPassword());
//            String encryptedconfirmpassword=EncryptionUtils.encrypt(admin.getConfirmPassword());

//            admin.setConfirmPassword(encryptedconfirmpassword);
            admin.setFirstname(admin.getFirstname());
            admin.setLastname(admin.getLastname());
            admin.setStatus("Active");
            admin.setPassword(encryptedpassword);
            admin.setEmail(email);
            admin.setPhone(phone);
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Date " + now);

            System.out.println("Encrypted Password SignUp " + encryptedpassword);
            adminRepository.save(admin);

        }else{
            System.out.println("User already Exist");

        }




        return admin;
    }


    public AdminLogin authAdmin(AdminLogin adminLogin){

        String encryptedpassword1= EncryptionUtils.encrypt(adminLogin.getPassword());
        String decryptedpassword=EncryptionUtils.decrypt(encryptedpassword1);
        System.out.println("Encryption Utils ENcrypt password " + encryptedpassword1);
        System.out.println("Encryption Utils Decrypt password " + decryptedpassword);


        if(!adminRepository.existsByEmail(adminLogin.getEmail())){
            System.out.println("User Does not exist ! Please Signup");
        }
        if(adminRepository.existsByEmail(adminLogin.getEmail())){
            if(adminRepository.existsByPassword(encryptedpassword1)){
                System.out.println("User Authenticated");
                return adminLogin;
            }
            System.out.println("admin password incorrect");

            return adminLogin;
        }
        System.out.println("admin invalid");
        return adminLogin ;


    }
}
