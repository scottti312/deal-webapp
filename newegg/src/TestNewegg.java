import java.util.*;


public class TestNewegg {
	 public static void main( String[] args )
	    {
	        String query = "fridge";
	        NeweggVendor bb = new NeweggVendor();
	        HashMap<String, String> test = bb.searchType("iphone");
	        System.out.println("hello");
	    }
}
