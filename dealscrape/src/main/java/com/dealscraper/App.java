package com.dealscraper;

public class App 
{
    public static void main( String[] args )
    {
        String query = "fridge";
        BestBuyVendor bb = new BestBuyVendor();
        bb.searchType(query);
    }
}
