package com.dealscraper;

import java.lang.annotation.Target;

public class App 
{
    public static void main( String[] args )
    {
        String query = "mousepad";
        BestBuyVendor bb = new BestBuyVendor();
        System.out.println(bb.searchType(query).toString(4));
        HomeDepotVendor hd = new HomeDepotVendor();
        CostcoVendor co = new CostcoVendor();
        NeweggVendor ne = new NeweggVendor();
        System.out.println(hd.searchType(query).toString(4));
        System.out.println(co.searchType(query).toString(4));
        System.out.println(ne.searchType(query).toString(4));
        // TargetVendor ta = new TargetVendor();
        // System.out.println(ta.searchType(query).toString(4));

    }
}
