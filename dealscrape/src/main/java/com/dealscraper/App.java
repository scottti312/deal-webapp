package com.dealscraper;

import java.lang.annotation.Target;

public class App 
{
    public static void main( String[] args )
    {
        // String query = "https://www.target.com/p/dove-white-moisturizing-beauty-bar-soap/-/A-84780837?preselect=11012602#lnk=sametab";
        // BestBuyVendor bb = new BestBuyVendor();
        // System.out.println(bb.searchType("iphone").toString(4));
        HomeDepotVendor hd = new HomeDepotVendor();
        // CostcoVendor co = new CostcoVendor();
        // NeweggVendor ne = new NeweggVendor();
        System.out.println(hd.searchType("whirlpool fridge").toString(4));
        // System.out.println(co.searchType("macbook").toString(4));
        // System.out.println(bb.searchType(query).toString(4));
        // System.out.println(ne.searchType(query).toString(4));
        // TargetVendor ta = new TargetVendor();
        // System.out.println(ta.searchType(query).toString(4));

    }
}
