package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.RatingData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;
import pl.edu.pw.ee.cosplay.rest.server.entity.McPhotoEntity;

import java.util.ArrayList;
import java.util.HashSet;

@RestController()
@RequestMapping(UrlData.GET_PHOTOS_LIST_PATH)
public class GetPhotosListController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] byteInput) {
        GetPhotosListInput input = (GetPhotosListInput) SerializationUtils.deserialize(byteInput);
        GetPhotosListOutput output = new GetPhotosListOutput();

        //TODO implementacja
        mockOutput(input, output);

        byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput, HttpStatus.OK);
    }

    private void mockOutput(GetPhotosListInput input, GetPhotosListOutput output) {
        output.setAreThereNextPhotos(false);
        ArrayList<SimplePhotoData> simplePhotoDataList = new ArrayList<>();

        Iterable<McPhotoEntity> photoList = photoDAO.findAll();

        for (McPhotoEntity photo : photoList) {
            SimplePhotoData simplePhoto = new SimplePhotoData();
            simplePhoto.setFranchisesList(new HashSet<String>());
            simplePhoto.setCharactersList(new HashSet<String>());
            simplePhoto.setCommentsNumber(0);
            simplePhoto.setPhotoBinaryData(
                    binaryPhotoDAO.findOne(1).getBinaryData()
            );
            simplePhoto.setAvatarBinaryData(
                    binaryPhotoDAO.findOne(1).getBinaryData()
            );
            simplePhoto.setUsername(photo.getUserByUsername().getUsername());
            simplePhoto.setUploadDate(photo.getUploadDate());
            RatingData ratingData = new RatingData();
            ratingData.setGeneralRate(0);
            ratingData.setArrangementRate(0);
            ratingData.setQualityRate(0);
            ratingData.setSimilarityRate(0);
            simplePhoto.setRatingData(ratingData);
            simplePhoto.setPhotoId(1);
            simplePhotoDataList.add(simplePhoto);
        }

        output.setSimplePhotoDataList(simplePhotoDataList);
    }

}
