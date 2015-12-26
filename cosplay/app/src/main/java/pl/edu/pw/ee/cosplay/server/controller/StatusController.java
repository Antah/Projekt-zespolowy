package pl.edu.pw.ee.cosplay.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import pl.edu.pw.ee.cosplay.server.ServerSettings;
import pl.edu.pw.ee.cosplay.server.model.McStatus;

/**
 * Created by Michał on 2015-12-24.
 */
public class StatusController {
    private StandardController<McStatus> statusStandardController = new StandardController<>("status");

    public ArrayList<McStatus> getStatus() throws IOException, ClassNotFoundException {
        return statusStandardController.getEntities();
        /*String url = ServerSettings.SERVER_URL + "/status";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IOException();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(response.getBody()));
        ArrayList<McStatus> status = (ArrayList<McStatus>) in.readObject();
        return status;*/
    }

    public McStatus getStatus(Long statusId) throws IOException, ClassNotFoundException {
        return statusStandardController.getEntity(statusId);
        /*String url = ServerSettings.SERVER_URL + "/status/" + statusId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IOException();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(response.getBody()));
        McStatus status = (McStatus) in.readObject();
        return status;*/
    }
}
