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
    }

    public McStatus getStatus(Long statusId) throws IOException, ClassNotFoundException {
        return statusStandardController.getEntity(statusId);
    }
}
