package plugins;
import java.util.*;

public interface IVendor {
    Map<String, String> searchType(String input);
    Map<String, String> search(String url);
    String find(String query);
}