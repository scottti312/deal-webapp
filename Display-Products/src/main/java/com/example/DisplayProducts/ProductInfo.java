package com.example.DisplayProducts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;


public class ProductInfo {
    public static JSONObject[] products() {
        String path = "src/main/resources/static/product.json";
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject o = new JSONObject(contents);
            JSONObject vendors[];
            vendors = new JSONObject[o.length()];
            Iterator<String> keys = o.keys();

            int itr = 0;

            if (o.length() != 0) {
                while(keys.hasNext()) {
                    String key = keys.next();
                    if(o.get(key) instanceof JSONObject) {

                        vendors[itr] = o.getJSONObject(key);
                        vendors[itr].put("vendor", key);
                        itr++;
                    }
                }
                return vendors;
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
