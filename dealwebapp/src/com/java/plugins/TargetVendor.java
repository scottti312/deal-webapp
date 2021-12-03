package com.java.plugins;

import java.io.IOException;
import java.util.*;
import com.java.dealinterface.IVendor;

public class TargetVendor implements IVendor{
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
    	//productMap.put("Target", "$800");
    	return "Target, Apple Watch Series 3 (GPS) 38mm Aluminum Case, $199";
    }
}
