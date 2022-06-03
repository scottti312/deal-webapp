package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

import com.example.DisplayProducts.deal.*;

@Controller
public class ProductController {

    @PostMapping("search")
    public String searchProduct(@RequestParam String productName) throws JSONException {
        if (productName.equals("")) {
            return "home";
        }
        ApplicationTwo app = new ApplicationTwo();
        org.json.simple.JSONObject simplejson = app.searchProduct(productName);
        JSONObject jsonobj = new JSONObject(simplejson.toString());
        System.out.println(jsonobj.toString(4));
        try {
            FileWriter file = new FileWriter (ApplicationTwo.superPath + "main/resources/static/product.json");
            file.write(jsonobj.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/pages/results";
    }

    @GetMapping("pages/results")
    String getProduct(Model model) throws JSONException {
        JSONObject product_data[] = ProductInfo.products();
        boolean userLoggedIn = CognitoClient.loggedIn;
        String idToken = CognitoClient.idToken;
        System.out.println("userLoggedIn" + userLoggedIn);
        model.addAttribute("idToken", idToken);
        model.addAttribute("vendors", product_data);
        model.addAttribute("userLoggedIn", userLoggedIn);

        return "pages/results";
    }

    @PostMapping("product-saved")
    public String saveProduct(@ModelAttribute ProductForm productForm, Model model) {
        String productUrl = productForm.getProductUrl();
        String imgUrl = productForm.getImgUrl();
        String vendorName = productForm.getVendorName();
        String productName = productForm.getProductName();
        String productPrice = productForm.getProductPrice();
        model.getAttribute("idToken");
        DynamoClient dbClient = new DynamoClient();
        dbClient.putItemInTable(LoginController.userEmail, productUrl, productName, imgUrl, productPrice, vendorName);
        model.addAttribute("productForm", productForm);
        return "redirect:/pages/results";
    }

}
