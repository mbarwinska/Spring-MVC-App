package pl.sda.intermediate.app.users;

import org.springframework.stereotype.Service;

@Service
public class UserContextHolder {
    private static String login;

    public static void addUserLogin(String email){
        login = email;
    }
    public static void logout(){
        login = null;
    }

    public static String getUserLoggedIn(){
        return login;
    }
}
