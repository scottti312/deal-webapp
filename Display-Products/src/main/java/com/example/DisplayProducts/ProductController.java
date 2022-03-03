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
    public String searchProduct(@RequestParam String productName) throws JSONException {
        System.out.println(productName);
        if (productName.equals("")) {
            return "home";
        }
        ApplicationTwo app = new ApplicationTwo();
        org.json.simple.JSONObject simplejson = app.searchProduct(productName);
        JSONObject jsonobj = new JSONObject(simplejson.toString());
        System.out.println(jsonobj.toString(4));
        return "redirect:/pages/results";
    }


    @GetMapping("pages/results")
    String getProduct(Model model, JSONObject jsonobj) throws JSONException {
        JSONObject product_data[] = ProductInfo.products(jsonobj);

        model.addAttribute("vendors", product_data);

        return "pages/results";
    }
}
