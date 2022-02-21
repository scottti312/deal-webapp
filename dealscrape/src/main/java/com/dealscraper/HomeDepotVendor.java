package com.dealscraper;

import java.io.IOException;
import java.util.List;
import java.io.File;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class HomeDepotVendor implements IVendor {
    public void searchType(String input) {
        if (input.contains(".com")) {
            generateProductInfo(input);
        } else {
            generateProductInfo(getProductUrl(input));
        }        
    }  
    
    public void generateProductInfo(String url) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productBrand = page.getFirstByXPath(".//span[@class='product-details__brand--link']");
            if (productBrand == null) {
                productBrand = page.getFirstByXPath(".//h2[@class='product-details__brand-name']");
                System.out.println(productBrand);
            }
            HtmlElement productName = page.getFirstByXPath(".//h1[@class='product-details__title']");
            List<HtmlElement> priceElement = page.getByXPath(".//div[@class='price-format__large price-format__main-price']//span");
            Double price = Double.parseDouble(priceElement.get(1).asNormalizedText()) + 
                           (Double.parseDouble(priceElement.get(2).asNormalizedText()) / 100);
            HtmlElement image = page.getFirstByXPath(".//div[@class='mediagallery']//.//img");
            Item item = new Item();
            item.setTitle(productBrand.asNormalizedText() + " " + productName.asNormalizedText());
            item.setPrice(price);
            item.setImage(image.getAttribute("src"));
            item.setVendor("HomeDepot");
            item.setLink(url);
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("homedepot_product.json"), item);
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
        String url = "https://www.homedepot.com/s/" + query;
        String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productResult = page.getFirstByXPath(".//section[@id='browse-search-pods-1']//.//a[@class='header product-pod--ie-fix']");
            productUrl = productResult.getAttribute("href");
            productUrl = "https://www.homedepot.com" + productUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return productUrl;
    }
    
}
