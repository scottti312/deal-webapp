import java.io.*;
import java.util.*; 
import com.java.dealinterface.IVendor;
import java.io.IOException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.*;

public class BestBuyVendor implements IVendor {
	 public HashMap<String, String> searchType(String input) {
        if (input.contains(".com")) {
            return generateProductInfo(input);
        } else {
            return generateProductInfo(getProductUrl(input));
        }        
    }  
    
    public HashMap<String, String> generateProductInfo(String url) {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);
        JSONObject item = new JSONObject();
        HashMap<String, String> result = null;
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
            
            result = new Gson().fromJson(item.toString(), new TypeToken<HashMap<String, String>>(){}.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return result;
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