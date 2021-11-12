import java.io.IOException;

public class AmazonVendor implements IVendor{
    public String search(String url) {
        String cmd = "python search.py";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        System.out.println();
        return null;
    }
}
