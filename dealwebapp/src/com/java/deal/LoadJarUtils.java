package com.java.deal;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadJarUtils {
	public static void loadJar(String jarPath) {
		URL url = null;
		try {
			File jarFile = new File(jarPath);
			url = jarFile.toURI().toURL();
			
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(url != null) {
			Method addURL = null;
			try {
				addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			}catch(NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			boolean accessible = addURL.isAccessible();
			try {
				addURL.setAccessible(true);
				URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
				addURL.invoke(classLoader,  url);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				addURL.setAccessible(accessible);
			}
		}
	}
}
