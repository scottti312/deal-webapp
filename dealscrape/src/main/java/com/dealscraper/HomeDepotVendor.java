package com.dealscraper;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.json.JSONObject;

public class HomeDepotVendor implements IVendor {
    public JSONObject searchType(String input) {
        if (input.contains(".com")) {
            return generateProductInfo(input);
        } else {
            return generateProductInfo(getProductUrl(input));
        }        
    }  
    
    public JSONObject generateProductInfo(String url) {
        JSONObject item = new JSONObject();
        item.put("title", "null");
        item.put("price", "null");
        item.put("image", "null");
        item.put("vendor", "HomeDepot");
        item.put("link", "null");
        if (url == null) 
            return item;
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productBrand = page.getFirstByXPath(".//span[@class='product-details__brand--link']");
            if (productBrand == null) {
                productBrand = page.getFirstByXPath(".//h2[@class='product-details__brand-name']");
            }
            HtmlElement productName = page.getFirstByXPath(".//h1[@class='product-details__title']");
            List<HtmlElement> priceElement = page.getByXPath(".//div[@class='price']//div[@class='price-format__large price-format__main-price']//span");
            System.out.println(priceElement);
            Double price = Double.parseDouble(priceElement.get(1).asNormalizedText().replace(",", "")) + 
                           (Double.parseDouble(priceElement.get(2).asNormalizedText()) / 100);
            HtmlElement image = page.getFirstByXPath(".//div[@class='mediagallery']//.//img");
            if (productBrand != null) {
                item.put("title", productBrand.asNormalizedText() + " " + productName.asNormalizedText());
            } else {
                item.put("title", productName.asNormalizedText());
            }
            item.put("price", price);
            item.put("image", image.getAttribute("src"));
            item.put("vendor", "HomeDepot");
            item.put("link", url);
        } catch (Exception e) {
            client.close();
            return item;
        }
        client.close();
        return item;
    }

    public String getProductUrl(String query) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        String url = "https://www.homedepot.com/s/" + query;
        String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productResult = page.getFirstByXPath(".//a[@class='header product-pod--ie-fix']");
            productUrl = productResult.getAttribute("href");
            productUrl = "https://www.homedepot.com" + productUrl;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            client.close();
            return productUrl;
        }
        client.close();
        return productUrl;
    }
    
}
