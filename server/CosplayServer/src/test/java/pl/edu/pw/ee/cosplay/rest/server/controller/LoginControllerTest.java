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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoOutput;

import java.net.URI;
import java.util.HashSet;

/**
 * Created by Micha� on 2016-05-18.
 */
public class LoginControllerTest {

    @Test
    public void loginTest(){
        try {
            final RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder
                    .fromHttpUrl(UrlData.getServerIp()).port(UrlData.getPORT()).path(UrlData.LOGIN_PATH).build().toUri();

            LoginControllerInput input = new LoginControllerInput();
            input.setUserName("Shafear");
            input.setPassword("password");

            byte[] binaryInput = SerializationUtils.serialize(input);

            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

            ResponseEntity<byte[]> response =  restTemplate.postForEntity(uri, binaryInput, byte[].class);

            LoginControllerOutput loginOutput = (LoginControllerOutput) SerializationUtils.deserialize(response.getBody());

            uri = UriComponentsBuilder
                    .fromHttpUrl(UrlData.getServerIp()).port(UrlData.getPORT()).path(UrlData.ADD_PHOTO_PATH).build().toUri();

            AddPhotoInput input2 = new AddPhotoInput();
            input2.setAuthenticationData(loginOutput.getAuthenticationData());
            input2.setCharactersList(new HashSet<String>());
            input2.setFranchisesList(new HashSet<String>());
            byte[] inputBytes = SerializationUtils.serialize("Shafear");
            input2.setPhotoBinaryData(inputBytes);
            input2.setPhotoDescription("Some description");

            binaryInput = SerializationUtils.serialize(input2);

            ResponseEntity<byte[]> response2 =  restTemplate.postForEntity(uri, binaryInput, byte[].class);

            AddPhotoOutput output2 = (AddPhotoOutput) SerializationUtils.deserialize(response2.getBody());


        } catch (HttpClientErrorException e){
            e.printStackTrace();
        }
    }
}