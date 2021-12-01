package user_login;
import java.sql.*;
import java.util.UUID;

public class UserRegister {
    public void register(String email, String username, String encryptedPass){

        String uniqueID = UUID.randomUUID().toString();
        System.out.println(uniqueID);
        // SQL command here
        String registerUser = "INSERT INTO user(iduser, email, join_date, last_login, password, username)" +
                              "VALUES (?, ?, \"1999-12-23\", \"1999-12-23 23:12:49\", ?, ?);";
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Change the user and password here to whatever
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","hi061498!");
            PreparedStatement ps = con.prepareStatement(registerUser);
            // Using PreparedStatement to avoid SQL injection attack, more secure
            ps.setString(1, uniqueID);
            ps.setString(2, email);
            ps.setString(3, encryptedPass);
            ps.setString(4, username);
            ps.executeUpdate();
            con.close();  
        } 
        catch(Exception e){ System.out.println(e);}  
    }
}
