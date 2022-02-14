package com.example.DisplayProducts;

import org.json.JSONException;
import org.json.JSONObject;

public class test1 {
    public static void main(String[] args) throws JSONException {
        JSONObject product_data[] = ProductInfo.products();
        System.out.println(product_data[2].getString("name"));
    }
}
