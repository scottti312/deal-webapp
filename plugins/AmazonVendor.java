package plugins;

import java.io.*;
import java.util.*;

public class AmazonVendor implements IVendor {
    public Map<String, String> searchType(String input) {
        Map<String, String> productInfo = new HashMap<>();
        productInfo.put("Title", "None");
        productInfo.put("Price", "None");
        productInfo.put("Image", "None");
        productInfo.put("Link", "None");
        productInfo.put("Vendor", "Walmart");
        if(input.contains(".com")) {
            // if the input is a link to a product,
            // get the product info
            return getProductInfo(input);
        } else {
            // if the input is a general search term,
            // find the link to a product then get the product info
            return getProductInfo(getUrl(input));
        }
    }

    public Map<String, String> getProductInfo(String url) {
        Map<String, String> dict = new HashMap<>();
        dict.put("Vendor", "Amazon");
        dict.put("Link", url);
        // The url must be surrounded by "" quotation marks
        url = String.format("\"%s\"", url);
        try {
            // Runs the command "python plugins/search(Amazon, BestBuy, etc.).py "url""
            ProcessBuilder pb = new ProcessBuilder("python", String.format("python/searchAmazon.py"), url);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            // Reads the returning strings printed from the python file
            String ret = in.readLine();
            dict.put("Title", ret);
            ret = in.readLine();
            dict.put("Price", ret);
            ret = in.readLine();
            dict.put("Image", ret);
            in.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return dict;
    }

    public String getUrl(String query) {
        String url = "";
        try {
            ProcessBuilder pb = new ProcessBuilder("python", String.format("python/searchAmazon.py"), query);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            url = in.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return url;
    }
}
