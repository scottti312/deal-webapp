package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {

    @PostMapping("search")
    public String searchProduct(@RequestParam String productName) {

        return "redirect:/pages/results";
    }


    @GetMapping("pages/results")
    String getProduct(Model model) throws JSONException {
        JSONObject product_data[] = ProductInfo.products();

        model.addAttribute("vendors", product_data);

        return "pages/results";
    }
}
