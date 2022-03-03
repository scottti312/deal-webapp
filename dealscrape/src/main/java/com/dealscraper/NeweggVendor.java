package com.dealscraper;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.json.JSONObject;

public class NeweggVendor implements IVendor{
    public JSONObject searchType(String input) {
        if (input.contains(".com")) {
            return generateProductInfo(input);
        } else {
            return generateProductInfo(getProductUrl(input));
        }        
    }  
    
    public JSONObject generateProductInfo(String url) {
        if (url == null) {
            JSONObject item = new JSONObject();
            item.put("title", "null");
            item.put("price", "null");
            item.put("image", "null");
            item.put("vendor", "Newegg");
            item.put("link", "null");
            return item;
        }
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        JSONObject item = new JSONObject();
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement title = page.getFirstByXPath(".//h1[@class='product-title']");
            List<HtmlElement> priceList = page.getByXPath(".//div[@class='product-price']//li[@class='price-current']");
            String price = "";
            for(HtmlElement priceElement : priceList) {
                HtmlElement dollars = priceElement.getFirstByXPath("strong");
                HtmlElement cents = priceElement.getFirstByXPath("sup");
                price += dollars.asNormalizedText();
                price += cents.asNormalizedText();
            }
            HtmlElement image = page.getFirstByXPath(".//div[@class='mainSlide']//.//img");
            item.put("title", title.asNormalizedText());
            item.put("price", Double.parseDouble(price));
            item.put("image", image.getAttribute("src"));
            item.put("vendor", "Newegg");
            item.put("link", url);
        } catch (IOException e) {
            e.printStackTrace();
            item.put("title", "null");
            item.put("price", "null");
            item.put("image", "null");
            item.put("vendor", "Newegg");
            item.put("link", "null");
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
        String url = "https://www.newegg.com/p/pl?d=" + query;
        String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productResult = page.getFirstByXPath(".//div[@class='item-cells-wrap border-cells items-grid-view four-cells expulsion-one-cell']//.//a[@class='item-title']");
            productUrl = productResult.getAttribute("href");
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
