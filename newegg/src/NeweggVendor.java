import java.io.IOException;
import java.util.*;
import com.java.dealinterface.IVendor;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

public class NeweggVendor implements IVendor{
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
            item.put("title", title.asNormalizedText());
            item.put("price", Double.parseDouble(price));
            item.put("image", image.getAttribute("src"));
            item.put("vendor", "Newegg");
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
        String url = "https://www.newegg.com/p/pl?d=" + query;
        String productUrl = null;
        try {
            HtmlPage page = client.getPage(url);
            HtmlElement productResult = page.getFirstByXPath(".//div[@class='item-container']//.//a[@class='item-title']");
            productUrl = productResult.getAttribute("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return productUrl;
    }
}
