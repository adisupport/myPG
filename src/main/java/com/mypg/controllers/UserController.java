package com.mypg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/signup")
public class UserController {
    String[] user = {"Name1","Name2","Name3"};

    @GetMapping()
    public String getSignup(Model model){
        model.addAttribute("user", "Aditya");
        model.addAttribute("nameArr",user);
        return "signup";
    }
    @PostMapping()
    public String postSignup(@ModelAttribute Map<String,String> formDetails){
        return "signup";
    }
}
