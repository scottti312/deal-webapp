import java.util.*;

class Main {
    public static void main(String[] args) {
        PluginSearch av = new PluginSearch();
        String[] urls = {"https://www.amazon.com/HIFIMAN-Full-Size-Audiophile-Adjustable-Headphone/dp/B07K59HW9R/?_encoding=UTF8&pd_rd_w=dKAIE&pf_rd_p=ed0a22d3-02a9-43b9-a7a8-2632c9eb2ad8&pf_rd_r=BMVWANTXYDRYBY2R497J&pd_rd_r=38ea6469-0b66-49b5-b0f1-0e243a5c4b4e&pd_rd_wg=uyxNX&ref_=pd_gw_ci_mcx_mr_hp_atf_m",
                         "https://www.newegg.com/core-black-msi-gp-series-gp66-leopard-11uh-032-gaming-entertainment/p/N82E16834155852?Item=N82E16834155852",
                         "https://www.walmart.com/ip/Sceptre-40-Class-FHD-1080P-LED-TV-X405BV-FSR/683540269",
                         "https://www.officedepot.com/a/products/5796074/Flexispot-V9-Desk-Exercise-Bike-With/;jsessionid=0000Qst9oCHbBRrC5OQcCxDPLzd:17h4h7dlm",
                         "https://www.bestbuy.com/site/samsung-odyssey-g7-28-ips-1ms-4k-uhd-freesync-g-sync-compatible-gaming-monitor-with-hdr-black/6463480.p?skuId=6463480"};
        String[] vendors = {"Amazon", "Newegg", "Walmart", "OfficeDepot", "BestBuy"};
        for (int i = 0; i < 5; i++) {
            Map<String, String> dict = av.search(urls[i], vendors[i]);
            System.out.println(dict.get("Vendor"));
            System.out.println(dict.get("Title"));
            System.out.println(dict.get("Price"));
        }
    }
}