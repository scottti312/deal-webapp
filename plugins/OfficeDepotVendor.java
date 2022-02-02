package plugins;

import java.io.*;
import java.util.*;

public class OfficeDepotVendor {
    public Map<String, String> search(String url) {
        Map<String, String> dict = new HashMap<>();
        dict.put("Vendor", "OfficeDepot");
        // The url must be surrounded by "" quotation marks
        url = String.format("\"%s\"", url);
        try {
            // Runs the command "python plugins/search(Amazon, BestBuy, etc.).py "url""
            ProcessBuilder pb = new ProcessBuilder("python", String.format("plugins/searchOfficeDepot.py"), url);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            // Reads the returning strings printed from the python file
            String ret = in.readLine();
            dict.put("Title", ret);
            ret = in.readLine();
            dict.put("Price", ret);
            ret = in.readLine();
            dict.put("Image", ret);
            in.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return dict;
    }
}
