package com.example.DisplayProducts;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    CognitoClient cc = new CognitoClient();
    public static String userEmail = "";

    @GetMapping("login")
    public String login() {

        return "pages/login";
    }

    @PostMapping("login-submit")
    public String submitLogin(@ModelAttribute LoginForm loginForm, Model model) {
        String email = loginForm.getEmailAddress();
        String password = loginForm.getPassword();
        cc.login(email, password);
        System.out.println(email);
        userEmail = email;
        System.out.println(password);
        model.addAttribute("loginForm", loginForm);
        return "pages/login-success";
    }

    @PostMapping("logout")
    public String logout(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        CognitoClient.loggedIn = false;
        userEmail = "";
        return "redirect:" + referrer;
    }
}