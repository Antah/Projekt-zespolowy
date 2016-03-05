package pl.edu.pw.ee.cosplay.client.rest.client;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.URI;

import pl.edu.pw.ee.cosplay.rest.model.entity.McUser;

/**
 * Created by Michał on 2016-02-02.
 */
public class LoginClient extends AsyncTask<Object, Object, Object> {
    @Override
    protected Object doInBackground(Object... params){
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final URI uri = UriComponentsBuilder.fromHttpUrl("http://192.168.1.4")
                    .port(8080)
                    .path("/login")
                    .queryParam("login", (String) params[0])
                    .queryParam("password", (String) params[1])
                    .build().toUri();
            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
            final ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);
            return SerializationUtils.deserialize(response.getBody());
        } catch (HttpClientErrorException e){
            e.printStackTrace();
            return e.getResponseBodyAsString();
        }
    }
}


