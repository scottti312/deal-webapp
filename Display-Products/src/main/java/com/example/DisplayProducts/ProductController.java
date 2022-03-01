package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DisplayProducts.deal.*;

@Controller
public class ProductController {

    @PostMapping("search")
    public String searchProduct(@RequestParam String productName) {
        System.out.println(productName);
        ApplicationTwo app = new ApplicationTwo();
        org.json.simple.JSONObject json = app.searchProduct(productName);
        System.out.println(json.toString());
        return "redirect:/pages/results";
    }


    @GetMapping("pages/results")
    String getProduct(Model model) throws JSONException {
        JSONObject product_data[] = ProductInfo.products();

        model.addAttribute("vendors", product_data);

        return "pages/results";
    }
}
