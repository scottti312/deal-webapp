package com.dealscraper;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.json.JSONObject;

public class BestBuyVendor implements IVendor {
    public JSONObject searchType(String input) {
        if (input.contains("bestbuy.com")) {
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
        item.put("vendor", "BestBuy");
        item.put("link", "null");
        if (url == null) {
            return item;
        }
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        try {
            // page contains all the html
            HtmlPage page = client.getPage(url);
            // The title is within an <h1> tag and contains a class attribute
            HtmlElement title = page.getFirstByXPath(".//h1[@class='heading-5 v-fw-regular']");
            // The price is within a span within a div
            HtmlElement price = page.getFirstByXPath(".//div[@class='priceView-hero-price priceView-customer-price']//span[@aria-hidden='true']");
            HtmlElement image = page.getFirstByXPath(".//img[@class='primary-image']");
            String priceDisplay = price.asNormalizedText().replace(",", "");
            item.put("title", title.asNormalizedText());
            item.put("price", Double.parseDouble(priceDisplay.replace("$", "")));
            item.put("image", image.getAttribute("src"));
            item.put("vendor", "BestBuy");
            item.put("link", url);
        } catch (IOException e) {
            client.close();
            return item;
        } catch (NullPointerException e) {
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
        String url = "https://www.bestbuy.com/site/searchpage.jsp?st=" + query;
        String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productResult = page.getFirstByXPath(".//div[@id='main-results']//.//a");
            productUrl = productResult.getAttribute("href");
            productUrl = "https://www.bestbuy.com" + productUrl;
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
