package com.java.deal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test2 {
    public static void main(String[] args) {
		ApplicationTwo app = new ApplicationTwo();
		
		
		JSONObject tt = app.searchProduct("iphone");
		System.out.println(tt.get("Newegg"));
	}
}
