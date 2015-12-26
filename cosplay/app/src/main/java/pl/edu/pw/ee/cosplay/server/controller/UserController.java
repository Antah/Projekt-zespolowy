package pl.edu.pw.ee.cosplay.server.controller;

import android.net.UrlQuerySanitizer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import pl.edu.pw.ee.cosplay.server.ServerSettings;
import pl.edu.pw.ee.cosplay.server.model.McStatus;
import pl.edu.pw.ee.cosplay.server.model.McUser;

/**
 * Created by Michał on 2015-12-24.
 */
public class UserController {
    StandardController<McUser> userStandardController = new StandardController<>("users");

    public ArrayList<McUser> getUsers() throws IOException, ClassNotFoundException {
        return userStandardController.getEntities();
    }

    public McUser getUser(Long userId) throws IOException, ClassNotFoundException {
        return userStandardController.getEntity(userId);
    }

    public void postUser(McUser user) throws IOException {
        userStandardController.postEntity(user);
    }
}
