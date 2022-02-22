package com.dealscraper;

public class App 
{
    public static void main( String[] args )
    {
        String query = "fridge";
        BestBuyVendor bb = new BestBuyVendor();
        System.out.println(bb.searchType(query).getString("title"));
    }
}
