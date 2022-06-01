package com.example.DisplayProducts;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Controller
public class SavedProductsController {

    @GetMapping("saved-products")
    public String savedProducts(Model model)  {
        DynamoClient dbc = new DynamoClient();
        List<Map<String, AttributeValue>> results;
        results = dbc.scanTable("deal_wishlist", LoginController.userEmail);
        //System.out.println(results);
        /*for (Map<String, AttributeValue> result : results) {
            model.addAttribute("imageLinks", result.get("imageLink").s());
            System.out.println(result.get("imageLink").s());
            model.addAttribute("vendors", result.get("vendor").s());
            model.addAttribute("productTitles", result.get("productTitle").s());
            model.addAttribute("productLinks", result.get("productLink").s());
            model.addAttribute("prices", result.get("price").s());
        }*/
        model.addAttribute("results", results);
        model.addAttribute("userLoggedIn", CognitoClient.loggedIn);
        //model.addAttribute("userLoggedOut", CognitoClient.loggedIn = false);
        return "pages/saved-products";
    }

    @PostMapping("product-removed")
    public String removeProduct(@ModelAttribute ProductForm productForm, Model model) {
        String productUrl = productForm.getProductUrl();
        model.getAttribute("idToken");
        //System.out.println(productUrl);
        System.out.println(LoginController.userEmail);
        DynamoClient dbClient = new DynamoClient();
        String preHash = productUrl + LoginController.userEmail;
        String generatedHash = Integer.toString(preHash.hashCode());
        dbClient.deleteItem(generatedHash, LoginController.userEmail);
        model.addAttribute("productForm", productForm);
        System.out.println("success");
        return "redirect:/saved-products";
    }
}