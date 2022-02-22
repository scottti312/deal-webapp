package com.dealscraper;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.json.*;

public class TargetVendor implements IVendor {
    // final String PATH = "C:\\Program Files (x86)\\chromedriver.exe";
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

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        JSONObject item = new JSONObject();
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement title = page.getFirstByXPath(".//h1[@data-test='product-title']");
            String jsonString = driver.findElement(By.xpath("//script[@type='application/ld+json']")).getAttribute("innerHTML");
            JSONObject json = new JSONObject(jsonString);
            JSONArray arr = json.getJSONArray("@graph");
            String price = arr.getJSONObject(0).getJSONObject("offers").getString("price");
            String image = driver.findElement(By.xpath("//div[@class='slide--active']//img")).getAttribute("src");
            System.out.println(image);
            item.put("title", title.asNormalizedText());
            item.put("price", Double.parseDouble(price));
            item.put("image", image);
            item.put("vendor", "Target");
            item.put("link", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        driver.quit();
        return item;
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
