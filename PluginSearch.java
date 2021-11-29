import java.io.*;
import java.util.*;

public class PluginSearch implements IVendor{
    public Map<String, String> search(String url, String vendor) {
        Map<String, String> dict = new HashMap<>();
        // The url must be surrounded by "" quotation marks
        url = String.format("\"%s\"", url);
        try {
            // Runs the command "python plugins/search(Amazon, BestBuy, etc.).py "url""
            ProcessBuilder pb = new ProcessBuilder("python", String.format("plugins/search%s.py", vendor), url);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            // Reads the returning strings printed from the python file
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