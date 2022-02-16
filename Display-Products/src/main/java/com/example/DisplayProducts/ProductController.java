package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping
    String getProduct(Model model) throws JSONException {
        JSONObject product_data[] = ProductInfo.products();

        model.addAttribute("vendors", product_data);

        return "results_page";
    }
}
