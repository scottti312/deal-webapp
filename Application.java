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
			
			Object IVenderCreator = getIVenderPlugin("IVender");
			
			if(IVenderCreator != null) {
				if(IVenderCreator instanceof IVender) {
					productName = ((IVendor)IVenderCreator).search("https://www.amazon.com/Dell-OptiPlex-Desktop-Complete-Computer/dp/B00IOTZGOE/ref=sr_1_3?keywords=computer&qid=1636880460&sr=8-3");
					System.out.println(productName);
				}
			}
		
	}
	
	private static Object getIVenderPlugin(String interfaceName){
		Object object;
		try {
			if(plugins != null) {
			
			
				for(String s : plugins) {
					Class c = s.getClass();
					Class[] theInterfaces = c.getInterfaces();
					for(int i = 0; i < theInterfaces.length; i++) {
						String name = theInterfaces[i].getName();
						if(name == interfaceName) {
							object = Class.forName(s).getConstructor().newInstance();
							if(object != null)
								return object;}
					}
				
				}
			}
			
			
			
		}catch (InstantiationException e) {
		    throw new RuntimeException(e);
		  } catch (IllegalAccessException e) {
		    throw new RuntimeException(e);
		  } catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);			
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			new RuntimeException(e);
		}
		
		return null;
	}
	
	private static ArrayList<String> LoadPlugins(){
		ArrayList<String> allVenders = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		
		try {
			JSONArray figure = (JSONArray)parser.parse(new FileReader("config.json"));
			
			for(Object o : figure) {
				JSONObject venderList = (JSONObject) o;
				JSONArray plugins = (JSONArray)venderList.get("plugins");
				
				for(Object c : plugins) {
					allVenders.add((String)c);
				}				
			}
			
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		if(allVenders.size() == 0) 
			return null;
		else
			return allVenders;
	} 
}
