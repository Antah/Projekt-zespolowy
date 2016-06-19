package pl.edu.pw.ee.cosplay.rest.server.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.util.SerializationUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.login.LoginControllerOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.model.security.AuthenticationData;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Micha� on 2016-05-18.
 */
public class LoginControllerTest {

    @Test
    public void loginTest() {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder
                    .fromHttpUrl(UrlData.getServerIp()).port(UrlData.getPORT()).path(UrlData.GET_PHOTO_PATH).build().toUri();

            GetPhotoInput input = new GetPhotoInput();
            input.setPhotoId(1);

            byte[] binaryInput = SerializationUtils.serialize(input);

            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());

            ResponseEntity<byte[]> response = restTemplate.postForEntity(uri, binaryInput, byte[].class);

            GetPhotoOutput loginOutput = (GetPhotoOutput) SerializationUtils.deserialize(response.getBody());
            System.out.println(loginOutput.getDescription());


        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LoginControllerOutput output = new LoginControllerOutput();
        output.setAuthenticationData(new AuthenticationData("test", "test"));
        String json = objectMapper.writeValueAsString(output);
        System.out.println(json);

        LoginControllerOutput loginOutput = objectMapper.readValue(json, LoginControllerOutput.class);
        loginOutput.setAuthenticationData(null);
    }
}