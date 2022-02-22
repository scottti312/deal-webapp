package com.dealscraper;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.json.JSONObject;

// !!!!!!!!!! Currently not working do to captcha bot detection !!!!!!!!!!
public class AmazonVendor implements IVendor {
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
        try {
            HtmlPage page = client.getPage(url);
            System.out.println(page.asXml());
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        return null;
    }

    public String getProductUrl(String url) {
        return null;
    }
}
