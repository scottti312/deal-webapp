import java.util.*;

class Main {
    public static void main(String[] args) {
        AmazonVendor av = new AmazonVendor();
        Map<String, String> dict = av.search("test");
        System.out.println(dict.get("Title"));
        System.out.println(dict.get("Price"));
    }
}