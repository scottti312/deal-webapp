package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping
    String getProduct(Model model) throws JSONException {
        JSONObject product_data[] = ProductInfo.products();
        model.addAttribute("product_name", product_data[2].getString("name"));

        return "results_page";
    }
}
