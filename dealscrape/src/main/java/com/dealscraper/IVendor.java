package com.dealscraper;
public interface IVendor {
    void generateProductInfo(String url);
    String getProductUrl(String url);
}
