package com.dealscraper;

public class App 
{
    public static void main( String[] args )
    {
        String query = "dell laptop";
        // BestBuyVendor bb = new BestBuyVendor();
        // System.out.println(bb.searchType(query).getString("title"));
        HomeDepotVendor hd = new HomeDepotVendor();
        System.out.println(hd.searchType(query).getString("title"));
    }
}
