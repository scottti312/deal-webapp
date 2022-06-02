package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RetailerController {

    @GetMapping("retailers")
    String retailers(Model model)  {
        model.addAttribute("userLoggedIn", CognitoClient.loggedIn);
        System.out.println(CognitoClient.loggedIn);
        return "pages/retailers";
    }
}
