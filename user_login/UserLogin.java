package user_login;
import java.sql.*;

public class UserLogin {
    public void login(String username, String password) {
        String loginUser = "SELECT iduser, username, password FROM user " +
                           "WHERE username = ? AND password = ?";
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hi061498!");
            PreparedStatement ps = con.prepareStatement(loginUser);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            String userid = "";
            
            while (rs.next()) {
                userid = rs.getString("iduser");
                String pass = rs.getString("password");
                String user = rs.getString("username");
                System.out.format("userid: %s\nusername: %s\nencrypted password: %s\n", userid, user, pass);
            }
            if (userid.isEmpty()) {
                System.out.println("User does not exist or incorrect password entered.");
            }
            else {
                System.out.println("Logged in!");
            }
            con.close();  
        } 
        catch(Exception e){ System.out.println(e);}
    }
}
