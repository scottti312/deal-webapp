package com.dealscraper;
public interface IVendor {
    void searchType(String input);
    void generateProductInfo(String url);
    String getProductUrl(String url);
}
