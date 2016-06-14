package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import pl.edu.pw.ee.cosplay.rest.server.dao.RatingDAO;
import pl.edu.pw.ee.cosplay.rest.server.entity.McRatingEntity;
import pl.edu.pw.ee.cosplay.rest.server.security.LoggedUsers;

/**
 * AddVoteController
 */
@RestController()
@RequestMapping(UrlData.ADD_VOTE_PATH)
public class AddVoteController extends AutowiredController {

    @Autowired
    private RatingDAO ratingDAO;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addVote(@RequestBody byte[] byteInput) {
        AddVoteInput input = (AddVoteInput) SerializationUtils.deserialize(byteInput);
        if (LoggedUsers.isLogged(input.getAuthenticationData())) {

            final McRatingEntity rating = ratingDAO.getRatingByPhotoAndUser(
                    input.getAuthenticationData().getUsername(), input.getPhotoId());
            if (rating != null) {
                return alreadyRated();
            }

            AddVoteOutput output = new AddVoteOutput();

            mockOutput(input, output);

            byte[] byteOutput = SerializationUtils.serialize(output);
            return new ResponseEntity<>(byteOutput, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NOT_LOGGED, HttpStatus.UNAUTHORIZED);
        }
    }

    private ResponseEntity<?> alreadyRated() {
        return new ResponseEntity<>(ErrorMessage.USER_ALREADY_VOTED, HttpStatus.BAD_REQUEST);
    }

    private void mockOutput(AddVoteInput input, AddVoteOutput output) {
        McRatingEntity ratingEntity = new McRatingEntity();
        ratingEntity.setUserByUsername(userDAO.getUserByLogin(input.getAuthenticationData().getUsername()));
        ratingEntity.setPhotoByPhotoId(photoDAO.findOne(input.getPhotoId()));
        ratingEntity.setRatingSimilarity(input.getRatingData().getSimilarityRate());
        ratingEntity.setRatingQuality(input.getRatingData().getQualityRate());
        ratingEntity.setRatingArrangemnt(input.getRatingData().getArrangementRate());
        ratingDAO.save(ratingEntity);
    }
}

