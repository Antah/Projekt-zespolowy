package pl.edu.pw.ee.cosplay.rest.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ee.cosplay.rest.model.constants.UrlData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.SimplePhotoData;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListInput;
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.GetPhotosListOutput;

import java.util.ArrayList;

@RestController()
@RequestMapping(UrlData.GET_PHOTOS_LIST_PATH)
public class GetPhotosListController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] observedInput) {
        GetPhotosListInput input = (GetPhotosListInput) SerializationUtils.deserialize(observedInput);
        GetPhotosListOutput output = new GetPhotosListOutput();

        //TODO implementacja
        mockOutput(output);

        byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput,HttpStatus.OK);
    }

    private void mockOutput(GetPhotosListOutput output) {
        output.setAreThereNextPhotos(false);
        ArrayList<SimplePhotoData> simplePhotoDataList = new ArrayList<>();
        output.setSimplePhotoDataList(simplePhotoDataList);
    }

}
