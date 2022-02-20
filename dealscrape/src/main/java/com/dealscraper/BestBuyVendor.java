package com.dealscraper;

import java.io.IOException;
import java.io.File;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BestBuyVendor implements IVendor {
    public void generateProductInfo(String url) {
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
            Item item = new Item();
            item.setTitle(title.asNormalizedText());
            item.setPrice(Double.parseDouble(price.asNormalizedText().replace("$", "")));
            item.setImage(image.getAttribute("src"));
            item.setVendor("BestBuy");
            item.setLink(url);
            
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("bestbuy_product.json"), item);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
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
        }
        client.close();
        return productUrl;
    }
}
