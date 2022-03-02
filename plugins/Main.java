package plugins;

import java.util.*;

public class Main {
    public static void main(String[] args) {        
        // BestBuyVendor bb = new BestBuyVendor();
        // Map<String, String> dict = bb.searchType("https://www.bestbuy.com/site/apple-preowned-iphone-x-with-256gb-memory-cell-phone-unlocked-space-gray/6394667.p?skuId=6394667");
        // System.out.println("Vendor: " + dict.get("Vendor"));
        // System.out.println("Title: " + dict.get("Title"));
        // System.out.println("Price: " + dict.get("Price"));
        // System.out.println("Image: " + dict.get("Image"));
        // System.out.println("Link: " + dict.get("Link"));

        System.out.println("");
        NeweggVendor ne = new NeweggVendor();
        Map<String, String> dict1 = ne.searchType("wh-1000xm4");
        System.out.println("Vendor: " + dict1.get("Vendor"));
        System.out.println("Title: " + dict1.get("Title"));
        System.out.println("Price: " + dict1.get("Price"));
        System.out.println("Image: " + dict1.get("Image"));
        System.out.println("Link: " + dict1.get("Link"));
    }
}