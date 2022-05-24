package com.example.DisplayProducts;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Controller
public class RegisterController {

    String email = "";
    CognitoClient cc = new CognitoClient();

    @GetMapping("register")
    public String register() {
        return "pages/register";
    }

    @PostMapping("register-confirm")
    public String submitRegister(@ModelAttribute RegisterForm registerForm, Model model) {
        String userName = registerForm.getEmailAddress();
        String password = registerForm.getPassword();
        email = registerForm.getEmailAddress();
        cc.signUp(userName, email, password); 
        model.addAttribute("registerForm", registerForm);
        return "pages/register-confirm";
    }

    @PostMapping("register-success")
    public String submitConfirmation(@ModelAttribute RegisterForm registerForm, Model model) {
        String confirmationCode = registerForm.getEmailConfirmation();
        CognitoClient cc = new CognitoClient();
        System.out.printf("email:%s\nconfirmationCode:%s\n",email, confirmationCode);
        cc.confirmSignUp(email, confirmationCode);
        model.addAttribute("registerForm", registerForm);
        return "pages/register-success";
    }
}