package pl.edu.pw.ee.cosplay.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import pl.edu.pw.ee.cosplay.server.ServerSettings;
import pl.edu.pw.ee.cosplay.server.model.McUser;

/**
 * Created by Michał on 2015-12-26.
 */
public class StandardController<T> {

    private String path;

    protected StandardController(String path){
        this.path = path;
    }

    protected ArrayList<T> getEntities() throws IOException, ClassNotFoundException {
        String url = ServerSettings.SERVER_URL + "/"  + path;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IOException();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(response.getBody()));
        ArrayList<T> users = (ArrayList<T>) in.readObject();
        return users;
    }

    protected T getEntity(Long entityId) throws IOException, ClassNotFoundException {
        String url = ServerSettings.SERVER_URL + "/" + path + "/" + entityId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IOException();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(response.getBody()));
        T user = (T) in.readObject();
        return user;
    }

    protected void postEntity(T entity) throws IOException {
        String url = ServerSettings.SERVER_URL + "/" + path;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(entity);
        out.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ResponseEntity<String> response = restTemplate.postForEntity(url, bytes, String.class);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IOException();
    }
}
