import java.util.*;

public class TestHomeDepot {
	public static void main( String[] args )
    {
        String query = "iphone";
        HomeDepotVendor bb = new HomeDepotVendor();
        HashMap<String, String> test = bb.searchType(query);
        System.out.println("hello");
    }
}
