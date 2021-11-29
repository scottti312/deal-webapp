import java.io.*;
import java.util.*;

public class AmazonVendor implements IVendor{
    public Map<String, String> search(String url) {
        String cmd = "python searchAmazon.py";
        Map<String, String> dict = new HashMap<>();
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "plugins/searchAmazon.py");
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            dict.put("Title", ret);
            ret = in.readLine();
            dict.put("Price", ret);
            in.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return dict;
    }
}