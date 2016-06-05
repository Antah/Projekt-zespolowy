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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote.AddVoteInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.addvote.AddVoteOutput;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

/**
 * AddVoteController
 */
@RestController()
@RequestMapping(UrlData.ADD_VOTE_PATH)
public class AddVoteController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVote(@RequestBody byte[] byteInput) {
        AddVoteInput input = (AddVoteInput) SerializationUtils.deserialize(byteInput);
        if(LoggedUsers.isLogged(input.getAuthenticationData())){

            AddVoteOutput output = new AddVoteOutput();

            //TODO: Implementacja
            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private void mockOutput(AddVoteInput input, AddVoteOutput output) {
        //sprawdzić czy dany użytkownik nie dodał już czasem oceny dla danego zdjęcia (wtedy odpowiedni status 4xx + komunikat o błędzie)
        //jak nie dodał to dodać ocenę i zwrócić pusty output (nie potrzebuję żadnej informacji zwrotnej oprócz statusu
        //200
    }
}

