package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.CommentData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.RatingData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphoto.GetPhotoOutput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.model.entity.McPhotoEntity;
import pl.edu.pw.ee.cosplay.rest.server.dao.BinaryPhotoDAO;
import pl.edu.pw.ee.cosplay.rest.server.dao.PhotoDAO;

import java.util.ArrayList;
import java.util.HashSet;

@RestController()
@RequestMapping(UrlData.GET_PHOTO_PATH)
public class GetPhotoController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getPhoto(@RequestBody byte[] byteInput) {
        GetPhotoInput input = (GetPhotoInput) SerializationUtils.deserialize(byteInput);
        GetPhotoOutput output = new GetPhotoOutput();

        //TODO implementacja
        mockOutput(input, output);

        byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput,HttpStatus.OK);
    }


    @Autowired
    PhotoDAO photoDAO;

    @Autowired
    BinaryPhotoDAO binaryPhotoDAO;

    private void mockOutput(GetPhotoInput input, GetPhotoOutput output) {
        McPhotoEntity photo = photoDAO.findOne(1);

        output.setFranchisesList(new HashSet<String>());
        output.setCharactersList(new HashSet<String>());
        output.setPhotoBinaryData(
                    binaryPhotoDAO.findOne(1).getBinaryData()
        );

        output.setUsername(photo.getUsername());
        output.setUploadDate(photo.getUploadDate());
        RatingData ratingData = new RatingData();
        ratingData.setArrangementRate(2);
        ratingData.setQualityRate(3);
        ratingData.setSimilarityRate(4);
        output.setRatingData(ratingData);
        photo.setPhotoId(input.getPhotoId());

        ArrayList<CommentData> comments = new ArrayList<>();
        CommentData commentData = new CommentData();
        commentData.setUsername("Username1");
        commentData.setComment("My comment1");
        CommentData commentData2 = new CommentData();
        commentData2.setUsername("Username2");
        commentData2.setComment("My comment2");
        CommentData commentData3 = new CommentData();
        commentData3.setUsername("Username3");
        commentData3.setComment("My comment3");
        output.setComments(comments);
    }

}
