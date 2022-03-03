package com.dealscraper;

import java.lang.annotation.Target;

public class App 
{
    public static void main( String[] args )
    {
        String query = "desk";
        BestBuyVendor bb = new BestBuyVendor();
        HomeDepotVendor hd = new HomeDepotVendor();
        CostcoVendor co = new CostcoVendor();
        NeweggVendor ne = new NeweggVendor();
        System.out.println(bb.searchType(query).toString(4));
        System.out.println(hd.searchType(query).toString(4));
        System.out.println(co.searchType(query).toString(4));
        System.out.println(ne.searchType(query).toString(4));

    }
}
