package com.example.DisplayProducts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;


public class ProductInfo {
    public static JSONObject[] products() {
        // Change to relative path
        String path = "Display-Products/src/main/resources/static/product.json";
        try {
            String contents = new String((Files.readAllBytes(Paths.get(path))));
            JSONObject o = new JSONObject(contents);
            Iterator<String> keys = o.keys();

            int itr = 0;

            List<JSONObject> jList = new ArrayList<JSONObject>();
            if (o.length() != 0) {
                while(keys.hasNext()) {
                    String key = keys.next();
                    if(o.get(key) instanceof JSONObject) {
                        if (!o.getJSONObject(key).getString("title").equals("null")) {
                            jList.add(o.getJSONObject(key));
                            jList.get(itr).put("vendor", key);
                            itr++;
                        }
                    }
                }
                JSONObject vendors[];
                vendors = jList.toArray(new JSONObject[0]);
                return vendors;
            }

        // } catch (JSONException | IOException e) {
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
