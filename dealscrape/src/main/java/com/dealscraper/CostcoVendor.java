package com.dealscraper;

import java.io.IOException;
import java.io.File;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CostcoVendor implements IVendor{
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
            HtmlElement title = page.getFirstByXPath(".//h1[@automation-id='productName']");
            HtmlElement image = page.getFirstByXPath(".//img[@class='img-responsive']");
            // Costco hides the price on the product page, so instead I grab it from the search page.
            String newSearchUrl = "https://www.costco.com/CatalogSearch?dept=All&keyword=" + title.asNormalizedText();
            page = client.getPage(newSearchUrl);
            HtmlElement price = page.getFirstByXPath(".//div[@automation-id='itemPriceOutput_0']");
            System.out.println(price);
            Item item = new Item();
            item.setTitle(title.asNormalizedText());
            item.setPrice(Double.parseDouble(price.asNormalizedText().replace("$", "")));
            item.setImage(image.getAttribute("src"));
            item.setVendor("Costco");
            item.setLink(url);
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("costco_product.json"), item);
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
