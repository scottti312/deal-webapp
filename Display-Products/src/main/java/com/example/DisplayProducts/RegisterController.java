package com.example.DisplayProducts;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Controller
public class RegisterController {

    String clientId = "7cuj1pu58j6n7i1sv7tfhknq8g";
    String secretKey = "1m9k7peq0cdd3t92bpm26skvderdg5ikd546fbvrqda11j0mlls3";

    @GetMapping("register")
    public String register() {
        return "pages/register";
    }

    @PostMapping("register-confirm")
    public String submitRegister(@ModelAttribute RegisterForm registerForm, Model model) {
        CognitoIdentityProviderClient identityProviderClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_1)
                .build();
        String userName = registerForm.getEmailAddress();
        String password = registerForm.getPassword();
        String email = registerForm.getEmailAddress();
        CognitoAuth.signUp(identityProviderClient, clientId, secretKey, userName, password, email);
        model.addAttribute("registerForm", registerForm);
        return "pages/register-confirm";
    }

    @PostMapping("register-success")
    public String submitConfirmation(@ModelAttribute RegisterForm registerForm, Model model) {

        model.addAttribute("registerForm", registerForm);
        return "pages/register-success";
    }
}