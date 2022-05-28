package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping
    String home(Model model)  {
        model.addAttribute("userLoggedIn", CognitoClient.loggedIn);
        model.addAttribute("userLoggedOut", CognitoClient.loggedIn = false);
        return "home";
    }
}
