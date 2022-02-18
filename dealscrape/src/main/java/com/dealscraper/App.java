package com.dealscraper;

public class App 
{
    private static final String baseUrl = "https://www.bestbuy.com/site/dell-inspiron-7000-2-in-1-14-touch-screen-laptop-amd-ryzen-5-8gb-memory-256gb-solid-state-drive-blue/6458907.p?skuId=6458907";

    public static void main( String[] args )
    {
        // BestBuyVendor bb = new BestBuyVendor();
        // bb.generateProductInfo(baseUrl);
        // NeweggVendor ne = new NeweggVendor();
        // ne.generateProductInfo("https://www.newegg.com/pulsar-g12kbn-12-000w-generators/p/0MC-00M0-00004?Item=9SIA08C88W7564&cm_sp=Homepage_SS-_-P1_9SIA08C88W7564-_-02172022");
        TargetVendor ta = new TargetVendor();
        ta.generateProductInfo("https://www.target.com/p/dell-7480-laptop-core-i5-6300u-2-4ghz-16gb-512gb-m-2-sata-14in-fhd-windows-10-pro-64bit-webcam-manufacturer-refurbished/-/A-82599395#lnk=sametab");
    }
}
