package com.example.demo.service;

import com.example.demo.domain.ProductCategory;
import com.example.demo.domain.User;
import com.example.demo.domain.UserLogin;
import com.example.demo.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    //   private String email=user.getEmail();
    public List<ProductCategory> listAll() {
        return productCategoryRepository.findAll();

    }
    @Transactional
    public User saveuser(User user) {

//        if (!userRepository.existsByEmail(user.getEmail())) {
//            System.out.println("New Email" + user.getEmail());
//            String email= user.getEmail();
//            System.out.println("Email"+ email);
////
//            String encryptedpassword= EncryptionUtils.encrypt(user.getPassword());
//            String encryptedconfirmpassword=EncryptionUtils.encrypt(user.getConfirmPassword());
//
//            user.setConfirmPassword(encryptedconfirmpassword);
//            user.setPassword(encryptedpassword);
//            user.setEmail(email);
//            System.out.println("Encrypted Password SignUp " + encryptedpassword);
//            userRepository.save(user);
//
//        }else{
//            System.out.println("User already Exist");

//        }




        return user;
    }


    public UserLogin authUser(UserLogin userLogin){

//        String encryptedpassword1= EncryptionUtils.encrypt(userLogin.getPassword());
//        String decryptedpassword=EncryptionUtils.decrypt(encryptedpassword1);
//        System.out.println("Encryption Utils ENcrypt password " + encryptedpassword1);
//        System.out.println("Encryption Utils Decrypt password " + decryptedpassword);
//
//
//        if(!userRepository.existsByEmail(userLogin.getEmail())){
//            System.out.println("User Does not exist ! Please Signup");
//        }
//        if(userRepository.existsByEmail(userLogin.getEmail())){
//            if(userRepository.existsByPassword(encryptedpassword1)){
//                System.out.println("User Authenticated");
//                return userLogin;
//            }
//            System.out.println("user password incorrect");
//
//            return userLogin;
//        }
//        System.out.println("user invalid");
        return userLogin ;


    }
}
