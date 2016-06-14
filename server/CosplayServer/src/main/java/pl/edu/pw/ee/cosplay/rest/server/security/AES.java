package pl.edu.pw.ee.cosplay.rest.server.security;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by Michał on 2016-06-05.
 */
public class AES {

    public static String encrypt(String rawPassword) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder("salt?");
        String encryptedPassword = standardPasswordEncoder.encode(rawPassword);
        return encryptedPassword;
    }

    public static Boolean isPasswordCorrect(String password, String encryptedPassword) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder("salt?");
        return standardPasswordEncoder.matches(password, encryptedPassword);
    }
}
