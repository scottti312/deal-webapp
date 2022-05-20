package com.example.DisplayProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @GetMapping("register")
    public String register() {

        return "pages/register";
    }

    @PostMapping("register-confirm")
    public String submitRegister(@ModelAttribute RegisterForm registerForm, Model model) {

        model.addAttribute("registerForm", registerForm);
        return "pages/register-confirm";
    }

    @PostMapping("register-success")
    public String submitConfirmation(@ModelAttribute RegisterForm registerForm, Model model) {

        model.addAttribute("registerForm", registerForm);
        return "pages/register-success";
    }
}