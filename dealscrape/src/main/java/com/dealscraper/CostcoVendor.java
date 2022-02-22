package com.dealscraper;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.json.JSONObject;

public class CostcoVendor implements IVendor{
    public JSONObject searchType(String input) {
        if (input.contains(".com")) {
            return generateProductInfo(input);
        } else {
            return generateProductInfo(getProductUrl(input));
        }        
    }  

    public JSONObject generateProductInfo(String url) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        JSONObject item = new JSONObject();
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement title = page.getFirstByXPath(".//h1[@automation-id='productName']");
            HtmlElement image = page.getFirstByXPath(".//img[@class='img-responsive']");
            // Costco hides the price on the product page, so instead I grab it from the search page.
            String newSearchUrl = "https://www.costco.com/CatalogSearch?dept=All&keyword=" + title.asNormalizedText();
            page = client.getPage(newSearchUrl);
            HtmlElement price = page.getFirstByXPath(".//div[@automation-id='itemPriceOutput_0']");
            System.out.println(price);
            item.put("title", title.asNormalizedText());
            item.put("price", Double.parseDouble(price.asNormalizedText().replace("$", "")));
            item.put("image", image.getAttribute("src"));
            item.put("vendor", "Costco");
            item.put("link", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return item;
    }

    public String getProductUrl(String query) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        String url = "https://www.costco.com/CatalogSearch?dept=All&keyword=" + query;
        String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productResult = page.getFirstByXPath(".//div[@class='product-list grid']//.//a");
            productUrl = productResult.getAttribute("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return productUrl;
    }
    
}