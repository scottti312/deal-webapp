package com.java.deal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.java.dealinterface.IVendor;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import java.util.*;
import java.lang.reflect.*;


public class ApplicationTwo {
	private static ArrayList<String> plugins = null;
	
	public String testString(String name) {
		return name;
	}
	
	
	private String convertListToString(ArrayList<String> list) {
		String str = String.valueOf(list);
		return str;
	}
	
	public String searchProduct(String productName){
		plugins = LoadPlugins();
		String result = "";
		//LinkedList<Map<String, String>> productList = new LinkedList<Map<String, String>>();
		//ArrayList<String> productList = new ArrayList<String>();
		String interfaceName = "com.java.dealinterface.IVendor";
		List<IVendor> IVendorCreator = getIVendorPlugin(interfaceName);
		
		if(IVendorCreator != null) {
			for(int i = 0; i < IVendorCreator.size(); i++) {
				//if(oneIVendor instanceof IVendor) {
					//Map<String, String> product = ((IVendor)oneIVendor).search("https://www.amazon.com/Dell-OptiPlex-Desktop-Complete-Computer/dp/B00IOTZGOE/ref=sr_1_3?keywords=computer&qid=1636880460&sr=8-3");
				IVendor oneIVendor = IVendorCreator.get(i);	
				String product = oneIVendor.search("https://www.amazon.com/Dell-OptiPlex-Desktop-Complete-Computer/dp/B00IOTZGOE/ref=sr_1_3?keywords=computer&qid=1636880460&sr=8-3");
					//productList.add(product);
					result += product + ";";
				//}
			}
		}
		
		/*if(productList.size() != 0) {
			for(Map<String, String> oneProduct : productList) {
				for(String key : oneProduct.keySet()) {
					System.out.println(key + " : " + oneProduct.get(key));
				}
			}
		}
		if(productList.size() != 0)
			return productList;
		else
			return null;*/
		return result;
	}
	
	
	private static List<IVendor> getIVendorPlugin(String interfaceName){		
		List<IVendor> objectList = new LinkedList<IVendor>();
		
		
		try {
			if(plugins != null) {			
				for(String s : plugins) {
					
					String fullPath = "C:\\Users\\Ke_Surface\\Documents\\BC\\" + s;
					String className = s.replaceFirst("[.][^.]+$", "");
					
					try {
						File dir = new File(fullPath);
						URL loadPath = dir.toURI().toURL();
						URL[] classUrl = new URL[] {loadPath};
						ClassLoader cl = new URLClassLoader(classUrl);
						Class loadedClass = cl.loadClass(className);
						
						
						IVendor oneObject = (IVendor)loadedClass.newInstance();
						String product = oneObject.search("111");
						System.out.println(product);
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
			Object obj = parser.parse(new FileReader("C:\\Users\\Ke_Surface\\Documents\\BC\\workspace\\dealwebapp\\config.json"));
			JSONArray figure = new JSONArray();
			figure.add(obj);
			
			for(Object o : figure) {
				JSONObject vendorList = (JSONObject) o;
				JSONArray plugins = (JSONArray)vendorList.get("plugins");
				
				for(Object c : plugins) {
					allVendors.add((String)c);
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
