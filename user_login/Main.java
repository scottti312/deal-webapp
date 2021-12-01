package user_login;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        String username = "myusername", email = "myemail@outlook.com", password = "testpass123";
        PassEncryption pe = new PassEncryption();
        UserRegister ur = new UserRegister();

        ur.register(email, username, pe.encrypt(password));
        UserLogin ul = new UserLogin();
        ul.login(username, pe.encrypt(password));

    }
}
