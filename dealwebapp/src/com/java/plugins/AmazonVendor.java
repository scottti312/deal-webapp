package com.java.plugins;

import java.io.IOException;
import java.util.*;
import com.java.dealinterface.IVendor;

public class AmazonVendor implements IVendor{
    public String search(String url) {
       /* String cmd = "python search.py";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        System.out.println();
        return null;
        */
    	
    	//Map<String, String> productMap = new HashMap<String, String>();
    	//productMap.put("Amazon", "$1000");
    	return "Amazon, (Refurbished) Apple Watch Series 3 (GPS, 38MM) - Silver Aluminum Case with White Sport Band, $299.00";
    }
}

