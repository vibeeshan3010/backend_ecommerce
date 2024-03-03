package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/")
@Controller
public class NavController {

    @RequestMapping("/")
    public RedirectView redirectHome(){
        return new RedirectView("/login");
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }




    @GetMapping("/products")
    public String productsPage(){
        return "products";
    }
    @GetMapping("/users")
    public String getUserPage(){
        return "users";
    }

    @GetMapping("/register")
    public String getRegistrationPage(){
        return "register";
    }

    @GetMapping("/forgotpassword")
    public String getForgotPasswordPage(){
        return "forgotpassword";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(){
        return "dashboard";
    }

    @GetMapping("/addproducts")
    public String addProducts(){
        return "addproducts";
    }

}
