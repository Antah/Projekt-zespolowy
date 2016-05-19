package pl.edu.pw.ee.cosplay.rest.server.controller;


import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.SerializationUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.observed.ObservedInputData;

import java.net.URI;

/**
 * Created by Micha³ on 2016-05-18.
 */
public class LoginControllerTest {

    @Test
    public void loginTest(){
        try {
            final RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder
                    .fromHttpUrl(UrlData.SERVER_IP).port(UrlData.PORT).path(UrlData.LOGIN_PATH).build().toUri();

            LoginControllerInput loginInput = new LoginControllerInput("test", "test");
            byte[] input = SerializationUtils.serialize(loginInput);

            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

            ResponseEntity<byte[]> response =  restTemplate.postForEntity(uri, input, byte[].class);

            LoginControllerOutput loginOutput = (LoginControllerOutput) SerializationUtils.deserialize(response.getBody());

            uri = UriComponentsBuilder
                    .fromHttpUrl(UrlData.SERVER_IP).port(UrlData.PORT).path(UrlData.OBSERVED_PATH).build().toUri();

            ObservedInputData observedInput = new ObservedInputData(loginOutput.getAuthorizationData(), "test");
            input = SerializationUtils.serialize(observedInput);

            ResponseEntity<String> response2 =  restTemplate.postForEntity(uri, input, String.class);

            String observedOutput = response2.getBody();


        } catch (HttpClientErrorException e){
            e.printStackTrace();
        }
    }
}