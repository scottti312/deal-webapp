package plugins;
import java.util.*;

public interface IVendor {
    Map<String, String> searchType(String input);
    Map<String, String> getProductInfo(String url);
    String getUrl(String query);
}