import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class CreateJson {
    private static FileWriter file;
    public static void main(String[] args) {
        String[] vendorList = {"Amazon", "Newegg", "Walmart", "OfficeDepot", "BestBuy"};
        ArrayList<JSONObject> vendors = new ArrayList<JSONObject>();
        // for ()

        JSONObject all = new JSONObject();
        JSONObject amazon = new JSONObject();
        amazon.put("id", "183bcb81-33cd-4319-bfc0-c40839941872");
        amazon.put("name", "Dell Inspiron 15 3501, 15.6 inch FHD Non-Touch Laptop - Intel Core i7-1165G7, 16GB DDR4 RAM, 512GB SSD, Intel Iris Xe Graphics, Windows 10 Home - Accent Black");
        amazon.put("price", 779.98);
        amazon.put("image", "https://m.media-amazon.com/images/I/71dAcuFsKuL._AC_UY218_.jpg");
        JSONObject bestBuy = new JSONObject();
        bestBuy.put("id", "d29c43b4-7e7a-438d-94e2-ae0e19e9b818");
        bestBuy.put("name", "Dell - Inspiron 2-in-1 14\" Touch-Screen Laptop - Intel Core i5 - 8GB Memory - 512GB Solid State Drive - Silver");
        bestBuy.put("price", 599.99);
        bestBuy.put("image", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6458/6458905_sd.jpg;maxHeight=200;maxWidth=300");
        JSONObject newegg = new JSONObject();
        newegg.put("id", "3e3ba6d5-3216-46c3-b5e7-e1a3ee61784f");
        newegg.put("name", "New Dell Inspiron Laptop 15.6\" Full HD Touch Screen Intel core i5-1135G7 16GB DDR4 RAM 512GB NVMe SSD Windows 11 Home Black");
        newegg.put("price", 726.00);
        newegg.put("image", "https://c1.neweggimages.com/ProductImageCompressAll1280/B295D211231147ZV9F0.jpg");
        JSONObject officeDepot = new JSONObject();
        officeDepot.put("id", "8a8c10c7-ebaa-4b23-af3e-1780c7cc6e82");
        officeDepot.put("name", "Dell™ Inspiron 3511 Laptop, 15.6\" Touchscreen, Intel® Core™ i7, 16GB Memory, 512GB Solid State Drive, Windows® 11, I3511-7658BLK-PUS");
        JSONObject target = new JSONObject();
        JSONObject walmart = new JSONObject();
        all.put("amazon", amazon);
        
        try {
        file = new FileWriter("result.json");
        file.write(all.toJSONString());
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
