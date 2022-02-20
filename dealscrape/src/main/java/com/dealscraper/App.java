package com.dealscraper;

public class App 
{
    public static void main( String[] args )
    {
        BestBuyVendor bb = new BestBuyVendor();
        bb.generateProductInfo(bb.getProductUrl("iphone"));
        NeweggVendor ne = new NeweggVendor();
        ne.generateProductInfo(ne.getProductUrl("dell laptop"));
        HomeDepotVendor hd = new HomeDepotVendor();
        hd.generateProductInfo(hd.getProductUrl("screwdriver"));
        CostcoVendor co = new CostcoVendor();
        co.generateProductInfo(co.getProductUrl("apple watch"));
    }
}
