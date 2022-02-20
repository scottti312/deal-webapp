package com.dealscraper;

import java.io.IOException;
import java.io.File;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.json.*;

public class TargetVendor implements IVendor {

    // final String PATH = "C:\\Program Files (x86)\\chromedriver.exe";

    public void generateProductInfo(String url) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement title = page.getFirstByXPath(".//h1[@data-test='product-title']");
            String jsonString = driver.findElement(By.xpath("//script[@type='application/ld+json']")).getAttribute("innerHTML");
            JSONObject json = new JSONObject(jsonString);
            JSONArray arr = json.getJSONArray("@graph");
            String price = arr.getJSONObject(0).getJSONObject("offers").getString("price");
            String image = driver.findElement(By.xpath("//div[@class='slide--active']//img")).getAttribute("src");
            // HtmlElement price = page.getFirstByXPath(".//span[@data-test='product-price']");
            // HtmlElement image = page.getFirstByXPath("//div[@class='slide--active']//img");
            System.out.println(image);
            Item item = new Item();
            item.setTitle(title.asNormalizedText());
            item.setPrice(Double.parseDouble(price));
            item.setImage(image);
            item.setVendor("Target");
            item.setLink(url);
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("target_product.json"), item);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        driver.quit();
    }

    public String getProductUrl(String query) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        String url = "https://www.target.com/s?searchTerm=" + query;
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // WebDriver driver = new ChromeDriver(options);
        // driver.get(url);
        // String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            System.out.println(page.asXml());
            // WebElement productResult = driver.findElement(By.xpath("//a[@data-test='product-title']"));
            // System.out.println(productResult.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
        return "";
    }
}
