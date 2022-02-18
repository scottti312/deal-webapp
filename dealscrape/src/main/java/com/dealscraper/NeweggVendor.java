package com.dealscraper;

import java.io.IOException;
import java.util.List;
import java.io.File;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.Html;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class NeweggVendor {
    public void generateProductInfo(String url) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement title = page.getFirstByXPath(".//h1[@class='product-title']");
            List<HtmlElement> priceList = page.getByXPath(".//li[@class='price-current']");
            String price = "";
            for(HtmlElement priceElement : priceList) {
                HtmlElement dollars = priceElement.getFirstByXPath("strong");
                HtmlElement cents = priceElement.getFirstByXPath("sup");
                price += dollars.asNormalizedText();
                price += cents.asNormalizedText();
            }
            HtmlElement image = page.getFirstByXPath(".//div[@class='mainSlide']//.//img");
            Item item = new Item();
            item.setTitle(title.asNormalizedText());
            item.setPrice(Double.parseDouble(price));
            item.setImage(image.getAttribute("src"));
            item.setVendor("Newegg");
            item.setLink(url);
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("newegg_product.json"), item);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
    }
}
