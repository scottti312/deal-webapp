package com.dealscraper;

import org.json.JSONObject;

public interface IVendor {
    JSONObject searchType(String input);
    JSONObject generateProductInfo(String url);
    String getProductUrl(String url);
}
