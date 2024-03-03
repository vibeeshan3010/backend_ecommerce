package com.example.demo.controller;


import com.example.demo.domain.ForgotPassword;
import com.example.demo.domain.User;
import com.example.demo.domain.UserLogin;
import com.example.demo.domain.userHistory;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.*;
import com.example.demo.service.UserService;
import com.example.demo.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Replace with the appropriate origin
//@RequestMapping(value = "/api/user")

public class UserController {
    @Autowired
    private UserService userService;
//    private userHistory userHistory;

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private UserHistoryRepository userHistoryRepository;
    private int attempts=UserLogin.attempts;
//    String email=user.getEmail();


    @PostMapping(value = "/saveuser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationResponse saveUser(@RequestBody User user) {

        boolean userExists = userRepository.existsByEmail(user.getEmail());
        if (userExists) {
            // User already exists, return a custom response with the status and message
            return new UserRegistrationResponse("User already exist for given Email", null);
        } else {
            // Save the user using the userService


            User savedUser = userService.saveuser(user);
            if (savedUser != null) {
                // Return a success response with the saved user data
                return new UserRegistrationResponse("User registration Successful", savedUser);
            } else {
                // Return an error response
                return new UserRegistrationResponse("Error", null);
            }
        }
    }




    @PostMapping(value = "/authuser")
    public AuthResponse authUser(@RequestBody UserLogin userLogin) {
        boolean userExist = userRepository.existsByEmail(userLogin.getEmail());
        String  status=userRepository.findStatusByEmail(userLogin.getEmail());
        LocalDateTime now=LocalDateTime.now();


        System.out.println("status" + status);
        if (userExist) {
            if (status.equals("Active")) {

                if(attempts>=3){
                    User user =userRepository.findByEmail(userLogin.getEmail());
                    user.setStatus("Blocked");
                    userLogin.setAttempts(0);
                    attempts=0;
                    userRepository.save(user);
                    System.out.println("Attempts " + attempts);
                    return new AuthResponse("User account is blocked due to invalid attempts",userLogin);
                }
                String password = userLogin.getPassword();

                String encryptedpassword = EncryptionUtils.encrypt(password);
                String decryptedpassword = EncryptionUtils.decrypt(encryptedpassword);
                System.out.println("Encryption Utils ENcrypt password in user controller auth" + encryptedpassword);
                System.out.println("Encryption Utils Decrypt password in user controller auth" + decryptedpassword);
                if (userRepository.existsByPassword(encryptedpassword)) {
                    System.out.println("EncryptedPassword" + encryptedpassword);
                    System.out.println("User is authenticated");
                    userLogin.setLoggedIn(true);

                    userHistory userhistory = new userHistory();
                    userhistory.setLoggedInTime(LocalDateTime.now());
                    userhistory.setEmail(userLogin.getEmail());
                    userhistory.setLoggedIn(true);
//                    userHistoryRepository.save(userhistory);
                    return new AuthResponse("User Authenticated", userLogin);


                }
//                UserLogin userLogin1 = us   erService.authUser(userLogin);

            } else if(status.equals("Blocked")) {
                return new AuthResponse("User account is blocked! Please contact support center", userLogin);
            }
            else{
                return new AuthResponse("User account is temperorily suspended! Please contact support center", userLogin);
            }
            attempts++;
            System.out.println("Attempts " + attempts);
            return new AuthResponse("Invalid username or password", userLogin);
        }else{
            return new AuthResponse("User does not exist!, Please SignUp", userLogin);

        }
    }






    @GetMapping(value="/getuserdetails")
    public ResponseEntity<User> getuserdetails(@RequestParam String email){
        User userexist=userRepository.findByEmail(email);
        if(userexist!=null){
            System.out.println("User Exist " + userexist);
            User user=userRepository.findByEmail(email);
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping(value="/forgotpassword")
    public ForgotPasswordResponse forgotPassword(@RequestBody ForgotPassword forgotPassword){
        boolean userExist = userRepository.existsByEmail(forgotPassword.getEmail());
        System.out.println(userExist);
        if(userExist){
            System.out.println("Email Exist");
            System.out.println("Email" + forgotPassword.getEmail());

            String newPassword= forgotPassword.getPassword();
            System.out.println("newPassword " +newPassword);

            User user = userRepository.findByEmail(forgotPassword.getEmail());

            String encryptedpassword= EncryptionUtils.encrypt(newPassword);
            System.out.println("Encrypted Password " + encryptedpassword);
//            user.setPassword(encryptedpassword);
            user.setPassword(encryptedpassword);
            User updateUserProfile = userRepository.save(user);
            System.out.println("Password Updated");
//            user.setConfirmPassword(encryptedpassword);

            return  new ForgotPasswordResponse("Password updated successfully","");

        }else{
            System.out.println("Email does not Exist");
            return  new ForgotPasswordResponse("","Password update failed");
        }
    }


    @PutMapping(value="/updateuserprofile")
    public UpdateUserProfileResponse updateuserprofile(@RequestBody User user){
        User updateUser=userRepository.findByEmail(user.getEmail());
        if(updateUser != null){
            System.out.println("User profile Exists");
            if (user.getFirstName() != null) {
                updateUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                updateUser.setLastName(user.getLastName());
            }
            if(user.getAddress() != null){
                updateUser.setAddress(user.getAddress());
            }
            if(user.getPhone() != null){
                updateUser.setAddress(user.getPhone());
            }

            User updateUserProfile = userRepository.save(updateUser);
            System.out.println("Updated");
            return  new UpdateUserProfileResponse(updateUserProfile,"Profile Updated Successfully"," ");

        }else{

            return  new UpdateUserProfileResponse(user,"","Profile update failed");
        }
    }

    @GetMapping(value = "/getallusers")
    public List<User> getAllUsers(){
        List<User> user= userService.listAll();
        return user;
    }

    @DeleteMapping(value = "/deleteuser")
    public DeleteUserResponse deleteUser(@RequestParam String email){
        User user=userRepository.findByEmail(email);
        if(user!=null ){

            userRepository.delete(user);
            return new DeleteUserResponse("User deleted successfully",user);

        }
        return new DeleteUserResponse("User not available",null);
}

    @PutMapping(value="/blockuser")
    public BlockUserResponse blockuser(@RequestParam String email){
        User user=userRepository.findByEmail(email);
        if(user!=null ){
            user.setStatus("Blocked");
            userRepository.save(user);
            return new BlockUserResponse("User blocked successfully",user);
        }
        return new BlockUserResponse("User not available",null);
    }

    @PutMapping(value="/unblockuser")
    public BlockUserResponse unblockuser(@RequestParam String email){
        User user=userRepository.findByEmail(email);
        if(user!=null ){
            user.setStatus("Active");
            userRepository.save(user);
            return new BlockUserResponse("User unblocked successfully",user);
        }
        return new BlockUserResponse("User not available",null);
    }


//    @PostMapping(value = "/logout")
//    public AuthResponse logoutUser(@RequestParam String email){
//        com.example.demo.domain.userHistory userHistory=userHistoryRepository.findByEmail(email);

//        if (userHistory.isLoggedIn()){
//            System.out.println("logged in is going out");
//            userHistory.setLoggedOutTime(LocalDateTime.now());
//            userHistory.setLoggedIn(false);
//        }
//        return new AuthResponse("user logout",null);
//    }



    }
