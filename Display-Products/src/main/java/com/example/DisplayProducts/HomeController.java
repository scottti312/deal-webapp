package com.example.DisplayProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping
    String home(Model model)  {
        model.addAttribute("userLoggedIn", CognitoClient.loggedIn);
        return "home";
    }
}
