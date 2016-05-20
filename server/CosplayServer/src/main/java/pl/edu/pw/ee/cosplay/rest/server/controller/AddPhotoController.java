package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.ErrorMessage;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addphoto.AddPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

@RestController()
@RequestMapping(UrlData.ADD_PHOTO_PATH)
public class AddPhotoController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] byteInput) {
        AddPhotoInput input = (AddPhotoInput) SerializationUtils.deserialize(byteInput);
        if(LoggedUsers.isLogged(input.getAuthenticationData())){

            AddPhotoOutput output = new AddPhotoOutput();

            //TODO: Implementacja
            mockOutput(output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private void mockOutput(AddPhotoOutput output) {
        output.setAddedPhotoId(1);
    }

}
