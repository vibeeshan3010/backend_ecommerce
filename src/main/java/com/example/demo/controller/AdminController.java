package com.example.demo.controller;


import com.example.demo.domain.Admin;
import com.example.demo.domain.AdminLogin;
import com.example.demo.domain.UserLogin;
import com.example.demo.domain.userHistory;
import com.example.demo.repository.AdminRepository;
import com.example.demo.response.AdminAuthResponse;
import com.example.demo.response.AdminRegistrationResponse;
import com.example.demo.service.AdminService;
import com.example.demo.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // Replace with the appropriate origin
//@RequestMapping(value = "/api/user")

public class AdminController {
    @Autowired
    private AdminService adminService;
//    private userHistory userHistory;

    @Autowired
    private AdminRepository adminRepository;


    //    @Autowired
//    private UserHistoryRepository userHistoryRepository;
    private int attempts= UserLogin.attempts;
//    String email=user.getEmail();
//    @GetMapping("/login")
//    @ResponseBody
//    public User getUserbyEmail(@RequestBody UserCredentials usercredentials) {
//        User user = userService.getUserByEmail(usercredentials.getEmail());
//        System.out.println(user.getFirstName());
//        return user;
//    }


    @PostMapping(value = "/registeradmin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AdminRegistrationResponse saveAdmin(@RequestBody Admin admin) {
        System.out.println("Admin Email " + admin.getEmail());
        boolean adminExist = adminRepository.existsByEmail(admin.getEmail());
        if (adminExist) {
            // User already exists, return a custom response with the status and message
            return new AdminRegistrationResponse("User already exist for given Email", null);
        } else {
            // Save the user using the userService
            Admin savedAdmin = adminService.saveAdmin(admin);
            if (savedAdmin != null) {
                // Return a success response with the saved user data
                return new AdminRegistrationResponse("User registration Successful", null);
            } else {
                // Return an error response
                return new AdminRegistrationResponse("Error", null);
            }
        }
    }




    @PostMapping(value = "/login")
    public ResponseEntity<AdminAuthResponse> authAdmin(@RequestBody AdminLogin adminLogin) {
        boolean adminExist = adminRepository.existsByEmail(adminLogin.getEmail());
        String status = adminRepository.findStatusByEmail(adminLogin.getEmail());
        LocalDateTime now = LocalDateTime.now();

        System.out.println("status" + status);

        if (adminExist) {
            if (status.equals("Active")) {
                if (attempts >= 3) {
                    Admin admin = adminRepository.findByEmail(adminLogin.getEmail());
                    admin.setStatus("Blocked");
                    adminLogin.setAttempts(0);
                    attempts = 0;
                    adminRepository.save(admin);
                    System.out.println("Attempts " + attempts);
                    String redirecturl="/";
                    return ResponseEntity.ok(new AdminAuthResponse("User account is blocked due to invalid attempts",redirecturl));
                }

                String password = adminLogin.getPassword();
                String encryptedpassword = EncryptionUtils.encrypt(password);

                if (adminRepository.existsByPassword(encryptedpassword)) {
                    System.out.println("EncryptedPassword" + encryptedpassword);
                    System.out.println("User is authenticated");
                    adminLogin.setLoggedIn(true);

                    userHistory userhistory = new userHistory();
                    userhistory.setLoggedInTime(LocalDateTime.now());
                    userhistory.setEmail(adminLogin.getEmail());
                    userhistory.setLoggedIn(true);
                    // Save user history if needed
                    // userHistoryRepository.save(userhistory);

                    // Assuming your dashboard URL is "/dashboard"
                    String redirectURL = "/dashboard";

                    return ResponseEntity.ok(new AdminAuthResponse("User Authenticated", redirectURL));
                }
            } else if (status.equals("Blocked")) {
                String redirecturl="/";
                return ResponseEntity.ok(new AdminAuthResponse("User account is blocked! Please contact the support center",redirecturl));
            } else {
                String redirecturl="/";
                return ResponseEntity.ok(new AdminAuthResponse("User account is temporarily suspended! Please contact the support center",redirecturl));
            }

            attempts++;
            String redirecturl="/";
            System.out.println("Attempts " + attempts);
            return ResponseEntity.ok(new AdminAuthResponse("Invalid username or password",redirecturl));
        } else {
            String redirecturl="/";
            return ResponseEntity.ok(new AdminAuthResponse("User does not exist! Please Sign Up",redirecturl));
        }
    }








//    @GetMapping(value="/getuserdetails")
//    public ResponseEntity<User> getuserdetails(@RequestParam String email){
//        User userexist=userRepository.findByEmail(email);
//        if(userexist!=null){
//            System.out.println("User Exist " + userexist);
//            User user=userRepository.findByEmail(email);
//            return ResponseEntity.ok(user);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//
//    }

//    @PutMapping(value="/forgotpassword")
//    public ForgotPasswordResponse forgotPassword(@RequestBody ForgotPassword forgotPassword){
//        boolean userExist = userRepository.existsByEmail(forgotPassword.getEmail());
//        System.out.println(userExist);
//        if(userExist){
//            System.out.println("Email Exist");
//            System.out.println("Email" + forgotPassword.getEmail());
//            String newPassword= forgotPassword.getPassword();
//            System.out.println("newPassword " +newPassword);
//
//            User user = userRepository.findByEmail(forgotPassword.getEmail());
//
//            String encryptedpassword= EncryptionUtils.encrypt(newPassword);
//            System.out.println("Encrypted Password " + encryptedpassword);
////            user.setPassword(encryptedpassword);
//            user.setPassword(encryptedpassword);
//            User updateUserProfile = userRepository.save(user);
//            System.out.println("Password Updated");
////            user.setConfirmPassword(encryptedpassword);
//
//            return  new ForgotPasswordResponse("Password updated successfully","");
//
//        }else{
//            System.out.println("Email does not Exist");
//            return  new ForgotPasswordResponse("","Password update failed");
//        }
//    }


//    @PutMapping(value="/updateuserprofile")
//    public UpdateUserProfileResponse updateuserprofile(@RequestBody User user){
//        User updateUser=userRepository.findByEmail(user.getEmail());
//        if(updateUser != null){
//            System.out.println("User profile Exists");
//            if (user.getFirstName() != null) {
//                updateUser.setFirstName(user.getFirstName());
//            }
//            if (user.getLastName() != null) {
//                updateUser.setLastName(user.getLastName());
//            }
//            if(user.getAddress() != null){
//                updateUser.setAddress(user.getAddress());
//            }
//            if(user.getPhone() != null){
//                updateUser.setAddress(user.getPhone());
//            }
//
//            User updateUserProfile = userRepository.save(updateUser);
//            System.out.println("Updated");
//            return  new UpdateUserProfileResponse(updateUserProfile,"Profile Updated Successfully"," ");
//
//        }else{
//
//            return  new UpdateUserProfileResponse(user,"","Profile update failed");
//        }
//    }

//    @GetMapping(value = "/getallusers")
//    public List<User> getAllUsers(){
//        List<User> user= userService.listAll();
//        return user;
//    }

//    @DeleteMapping(value = "/deleteuser")
//    public DeleteUserResponse deleteUser(@RequestParam String email){
//        User user=userRepository.findByEmail(email);
//        if(user!=null ){
//
//            userRepository.delete(user);
//            return new DeleteUserResponse("User deleted successfully",user);
//
//        }
//        return new DeleteUserResponse("User not available",null);
//    }

//    @PutMapping(value="/blockuser")
//    public BlockUserResponse blockuser(@RequestParam String email){
//        User user=userRepository.findByEmail(email);
//        if(user!=null ){
//            user.setStatus("Blocked");
//            userRepository.save(user);
//            return new BlockUserResponse("User blocked successfully",user);
//        }
//        return new BlockUserResponse("User not available",null);
//    }

//    @PutMapping(value="/unblockuser")
//    public BlockUserResponse unblockuser(@RequestParam String email){
//        User user=userRepository.findByEmail(email);
//        if(user!=null ){
//            user.setStatus("Active");
//            userRepository.save(user);
//            return new BlockUserResponse("User unblocked successfully",user);
//        }
//        return new BlockUserResponse("User not available",null);
//    }


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
