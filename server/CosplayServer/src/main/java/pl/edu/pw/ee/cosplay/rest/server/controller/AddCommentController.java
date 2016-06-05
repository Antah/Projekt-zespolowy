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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addcomment.AddCommentOutput;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

@RestController()
@RequestMapping(UrlData.ADD_COMMENT_CONTROLLER)
public class AddCommentController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody byte[] byteInput) {
        AddCommentInput input = (AddCommentInput) SerializationUtils.deserialize(byteInput);
        if (LoggedUsers.isLogged(input.getAuthenticationData())) {

            AddCommentOutput output = new AddCommentOutput();

            //TODO: Implementacja
            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private void mockOutput(AddCommentInput input, AddCommentOutput output) {

    }

}
