package com.example.DisplayProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {

        return "pages/login";
    }

    @PostMapping("login-submit")
    public String submitLogin(@ModelAttribute LoginForm loginForm, Model model) {
        String email = loginForm.getEmailAddress();
        String password = loginForm.getPassword();
        model.addAttribute("loginForm", loginForm);
        return "pages/login-success";
    }
}