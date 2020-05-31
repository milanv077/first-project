package com.zopsmart.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zopsmart.model.User;
import com.zopsmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Principal principal,Model model)
    {
        try {
            User user=userService.findUserByEmail(principal.getName());
            model.addAttribute("name",user.getFirstName());

        }
        catch (Exception e) {
        }
        return "home";
    }


    @GetMapping("/signup")
    public String registerPage(Model model)
    {
        model.addAttribute("user",new User());
        return "signup";
    }
    @GetMapping("/login")
    @HystrixCommand(fallbackMethod = "fallback_login", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String loginPage() throws InterruptedException {
        Thread.sleep(3000);
        return "signin";
    }
    public String fallback_login() {
        return "error";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user,Model model) throws InterruptedException {

        if(userService.findUserByEmail(user.getEmail())==null) {
            if(userService.findUserByMobileNumber(user.getMobileNumber())==null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(hashedPassword);
                userService.saveUser(user);
            }
            else {
                model.addAttribute("error","Mobile number already exist");
                return "signup";
            }
        }
        else{
            model.addAttribute("error","Email already exist");
            return "signup";
        }
        return "redirect:/login";
    }



}
