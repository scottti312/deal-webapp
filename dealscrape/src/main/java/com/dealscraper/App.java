package com.dealscraper;

public class App 
{
    public static void main( String[] args )
    {
        // String query = "laptop";
        BestBuyVendor bb = new BestBuyVendor();
        HomeDepotVendor hd = new HomeDepotVendor();
        CostcoVendor co = new CostcoVendor();
        NeweggVendor ne = new NeweggVendor();
        // System.out.println(co.getProductUrl("fridge"));
        // System.out.println(hd.searchType("headphones").toString(4));
        String[] queries = {"man", "headphones"};
        for (String query : queries) {
            System.out.println(bb.searchType(query).toString(4));
            System.out.println(hd.searchType(query).toString(4));
            System.out.println(co.searchType(query).toString(4));
            System.out.println(ne.searchType(query).toString(4));
        }

    }
}
