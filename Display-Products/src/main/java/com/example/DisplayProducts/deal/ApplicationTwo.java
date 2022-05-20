package com.example.DisplayProducts.deal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.DisplayProducts.dealinterface.IVendor;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.*;


public class ApplicationTwo {
	private static ArrayList<String> plugins = null;
	
	public String testString(String name) {
		return name;
	}
	
	public JSONObject searchProduct(String productName){
		plugins = LoadPlugins();
		
		List<IVendor> IVendorCreator = getIVendorPlugin();
		
		JSONObject productJson = new JSONObject();
		
		if(IVendorCreator != null) {
			for(int i = 0; i < IVendorCreator.size(); i++) {
				 
				IVendor oneIVendor = IVendorCreator.get(i);	
				HashMap<String, String> singleVendorMap = oneIVendor.searchType(productName);								
				
				String vendorName = "";
				JSONObject subJson = new JSONObject();
				
				for(String item : singleVendorMap.keySet()) {
					
					if(item.equals("vendor")) {
						vendorName += singleVendorMap.get(item);
					}else{
						subJson.put(item, singleVendorMap.get(item));
					}				
				}
				
				productJson.put(vendorName, subJson);				
			}
		}
		
		
		return productJson;
	}
	
	
	private static List<IVendor> getIVendorPlugin(){		
		List<IVendor> objectList = new LinkedList<IVendor>();
		
		
		try {
			if(plugins != null) {			
				for(String s : plugins) {
					
					String fullPath = "Display-Products/src/main/java/com/example/DisplayProducts/deal/plugins/" + s;
					String className = s.replaceFirst("[.][^.]+$", "");
					
					try {
						File dir = new File(fullPath);
						URL loadPath = dir.toURI().toURL();
						URL[] classUrl = new URL[] {loadPath};
						ClassLoader cl = new URLClassLoader(classUrl);
						Class loadedClass = cl.loadClass(className);
						
						
						IVendor oneObject = (IVendor)loadedClass.newInstance();
						
						if(oneObject != null)
							objectList.add(oneObject);
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				
				if(objectList.size() != 0)
					return objectList;
			}
			
			
			
		}catch (Exception e) {
		    e.printStackTrace();
		  }
		
		return null;
	}
	
	private static ArrayList<String> LoadPlugins(){
		ArrayList<String> allVendors = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("Display-Products/src/main/resources/config.json"));
			JSONArray figure = new JSONArray();
			figure.add(obj);
			
			for(Object o : figure) {
				JSONObject vendorList = (JSONObject) o;
				JSONArray plugins = (JSONArray)vendorList.get("plugins");
				
				for(Object c : plugins) {
					JSONObject onePluginList = (JSONObject) c;
					JSONArray onePlugin = new JSONArray();
					onePlugin.add(onePluginList);
					
					for(Object e : onePlugin) {
						JSONObject singlePlugin = (JSONObject) e;
						String vendorName = (String)singlePlugin.get("name");
					allVendors.add(vendorName);
					}
					
					
				}				
			}
			
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		if(allVendors.size() == 0) 
			return null;
		else
			return allVendors;
	} 
}
