import java.util.*;
import org.json.simple.JSONObject;

public class testplugin {
	 public static void main( String[] args )
	    {
	        String query = "fridge";
	        BestBuyVendor bb = new BestBuyVendor();
	        HashMap<String, String> test = bb.searchType("dell laptop");
	        System.out.println("hello");
	    }
}
