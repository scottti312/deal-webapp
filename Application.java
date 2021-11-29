package dealsapplicatioin;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import java.lang.reflect.*;



public class Application {
	
	private static ArrayList<String> plugins = null;
	
	public static void main(String args[]) {
		
			plugins = LoadPlugins();
			
			String productName = "";
			
			String interfaceName = "dealsapplicatioin.IVendor";
			List<Object> IVendorCreator = getIVendorPlugin(interfaceName);
			
			if(IVendorCreator != null) {
				for(Object oneIVendor : IVendorCreator) {
					if(oneIVendor instanceof IVendor) {
						productName = ((IVendor)oneIVendor).search("https://www.amazon.com/Dell-OptiPlex-Desktop-Complete-Computer/dp/B00IOTZGOE/ref=sr_1_3?keywords=computer&qid=1636880460&sr=8-3");
						System.out.println(productName);
					}
				}
			}
		
	}
	
	private static List<Object> getIVendorPlugin(String interfaceName){		
		List<Object> objectList = new LinkedList<Object>();
		
		
		try {
			if(plugins != null) {			
				for(String s : plugins) {
					String fileName = s.replaceFirst("[.][^.]+$", "");
					String packageName = "dealsapplicatioin";
					String fullPath = packageName + '.' + fileName;
					
					
							Class c = Class.forName(fullPath);
							Class[] theInterfaces = c.getInterfaces();
							
							for(int i = 0; i < theInterfaces.length; i++) {
								String name = theInterfaces[i].getName();
								if(name == interfaceName) {
									Object oneObject = c.newInstance();
									if(oneObject != null)
										objectList.add(oneObject);
								}
							}
					
				
					
				}
				
				if(objectList.size() != 0)
					return objectList;
			}
			
			
			
		}catch (InstantiationException e) {
		    throw new RuntimeException(e);
		  } catch (IllegalAccessException e) {
		    throw new RuntimeException(e);
		  } catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);			
		}catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	private static ArrayList<String> LoadPlugins(){
		ArrayList<String> allVendors = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("config.json"));
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
