package pl.edu.pw.ee.cosplay.rest.server.security;

import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;
import pl.edu.pw.ee.cosplay.rest.model.entity.McUserEntity;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Micha³ on 2016-05-19.
 */
public class LoggedUsers {

    private static HashMap<String, String> loggedUsers = new HashMap<>();

    public static AuthenticationData generateTokenAndAddToLoggedUsers(McUserEntity user){
        Random random = new Random();
        String token = String.valueOf((user.getUsername() + random.nextInt() + System.currentTimeMillis()).hashCode());
        AuthenticationData data = new AuthenticationData(user.getUsername(), token);
        if(loggedUsers.get(user.getUsername()) != null){
            loggedUsers.replace(user.getUsername(), token);
        } else {
            loggedUsers.put(user.getUsername(), token);
        }
        return data;
    }

    public static Boolean isLogged(AuthenticationData data){
        String actualToken = loggedUsers.get(data.getUsername());
        if(actualToken != null){
            return loggedUsers.get(data.getUsername()).equals(data.getToken());
        } else {
            return false;
        }
    }

}
