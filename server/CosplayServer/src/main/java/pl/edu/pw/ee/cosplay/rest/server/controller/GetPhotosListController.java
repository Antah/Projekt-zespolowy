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
import pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist.PhotosOrder;
import pl.edu.pw.ee.cosplay.rest.server.entity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@RestController()
@RequestMapping(UrlData.GET_PHOTOS_LIST_PATH)
public class GetPhotosListController extends AutowiredController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody byte[] byteInput) {
        GetPhotosListInput input = (GetPhotosListInput) SerializationUtils.deserialize(byteInput);
        GetPhotosListOutput output = new GetPhotosListOutput();

        mockOutput(input, output);

        byte[] byteOutput = SerializationUtils.serialize(output);
        return new ResponseEntity<>(byteOutput, HttpStatus.OK);
    }

    private void mockOutput(GetPhotosListInput input, GetPhotosListOutput output) {
        ArrayList<McPhotoEntity> photoEntities = (ArrayList<McPhotoEntity>) photoDAO.findAll();
        if (input.getAuthor() != null) {
            photoEntities = filterByAuthor(photoEntities, userDAO.getUserByLogin(input.getAuthor()));
        }
        if (input.getObserver() != null) {
            photoEntities = filterByObserver(photoEntities, userDAO.getUserByLogin(input.getObserver()));
        }
        photoEntities = filterByCharacterList(photoEntities, input.getFiltrByCharactersList());
        photoEntities = filterByFranchisesList(photoEntities, input.getFiltrByFranchiseList());
        photoEntities = orderBy(photoEntities, input.getOrder());
        ArrayList<SimplePhotoData> photoDatas = new ArrayList<>();
        for (int i = input.getRangeFirst(); i <= input.getRangeLast(); i++) {
            if ((i - 1) < photoEntities.size()) {
                photoDatas.add(McPhotoEntity.createPhotoDataFromPhotoEntity(new SimplePhotoData(), photoEntities.get(i - 1)));
            }
        }
        output.setSimplePhotoDataList(photoDatas);
        if (input.getRangeLast() < photoEntities.size()) {
            output.setAreThereNextPhotos(true);
        } else {
            output.setAreThereNextPhotos(false);
        }
    }

    private ArrayList<McPhotoEntity> orderBy(ArrayList<McPhotoEntity> photoEntities, PhotosOrder order) {
        switch (order) {
            case UPLOAD_DATE:
                Collections.sort(photoEntities, (o1, o2) -> o1.getUploadDate().compareTo(o2.getUploadDate()));
                break;
            case UPLOAD_DATE_DESC:
                Collections.sort(photoEntities, (o1, o2) -> o2.getUploadDate().compareTo(o1.getUploadDate()));
                break;
            case COMMENTS_NO:
                Collections.sort(photoEntities, (o1, o2) -> o2.getCommentsByPhotoId().size() - o1.getCommentsByPhotoId().size());
                break;
            case COMMENTS_NO_DESC:
                Collections.sort(photoEntities, (o1, o2) -> o1.getCommentsByPhotoId().size() - o2.getCommentsByPhotoId().size());
                break;
            case GENERAL_RATE:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o2).getGeneralRate()
                        .compareTo(McPhotoEntity.getRatingData(o1).getGeneralRate()));
                break;
            case GENERAL_RATE_DESC:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o1).getGeneralRate()
                        .compareTo(McPhotoEntity.getRatingData(o2).getGeneralRate()));
                break;
            case SIMILARITY_RATE:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o2).getSimilarityRate()
                        .compareTo(McPhotoEntity.getRatingData(o1).getSimilarityRate()));
                break;
            case SIMILARITY_RATE_DESC:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o1).getSimilarityRate()
                        .compareTo(McPhotoEntity.getRatingData(o2).getSimilarityRate()));
                break;
            case QUALITY_RATE:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o2).getQualityRate()
                        .compareTo(McPhotoEntity.getRatingData(o1).getQualityRate()));
                break;
            case QUALITY_RATE_DESC:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o1).getQualityRate()
                        .compareTo(McPhotoEntity.getRatingData(o2).getQualityRate()));
                break;
            case ARRANGEMENT_RATE:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o2).getArrangementRate()
                        .compareTo(McPhotoEntity.getRatingData(o1).getArrangementRate()));
                break;
            case ARRANGEMENT_RATE_DESC:
                Collections.sort(photoEntities, (o1, o2) -> McPhotoEntity.getRatingData(o1).getArrangementRate()
                        .compareTo(McPhotoEntity.getRatingData(o2).getArrangementRate()));
                break;
        }
        return photoEntities;
    }

    private ArrayList<McPhotoEntity> filterByFranchisesList(ArrayList<McPhotoEntity> photoEntities, HashSet<String> filtrByFranchiszesList) {
        ArrayList<McPhotoEntity> toRemove = new ArrayList<>();
        for (McPhotoEntity photoEntity : photoEntities) {
            for (String franchise : filtrByFranchiszesList) {
                Boolean isFranchise = false;
                for (McFranchiseEntity franchiseEntity : photoEntity.getFranchisesByPhotoId()) {
                    if (franchiseEntity.getFranchiseName().equals(franchise)) {
                        isFranchise = true;
                        break;
                    }
                }
                if (!isFranchise) {
                    toRemove.add(photoEntity);
                }
            }
        }
        photoEntities.removeAll(toRemove);
        return photoEntities;
    }

    private ArrayList<McPhotoEntity> filterByCharacterList
            (ArrayList<McPhotoEntity> photoEntities, HashSet<String> filtrByFranchiszesList) {
        ArrayList<McPhotoEntity> toRemove = new ArrayList<>();
        for (McPhotoEntity photoEntity : photoEntities) {
            for (String character : filtrByFranchiszesList) {
                Boolean isCharacter = false;
                for (McCharacteerEntity characteerEntity : photoEntity.getCharacteersByPhotoId()) {
                    if (characteerEntity.getCharacteerName().equals(character)) {
                        isCharacter = true;
                        break;
                    }
                }
                if (!isCharacter) {
                    toRemove.add(photoEntity);
                }
            }
        }
        photoEntities.removeAll(toRemove);
        return photoEntities;
    }

    private ArrayList<McPhotoEntity> filterByObserver(ArrayList<McPhotoEntity> photoEntities, McUserEntity
            observer) {
        ArrayList<McPhotoEntity> toRemove = new ArrayList<>();
        for (McPhotoEntity photoEntity : photoEntities) {
            Boolean isObserved = false;
            for (McObservationEntity observed : observer.getObservationsByUsername_0()) {
                if (observed.getUserByObserved() == photoEntity.getUserByUsername()) {
                    isObserved = true;
                    break;
                }
            }
            if (!isObserved) {
                toRemove.add(photoEntity);
            }
        }
        photoEntities.removeAll(toRemove);
        return photoEntities;
    }

    private ArrayList<McPhotoEntity> filterByAuthor(ArrayList<McPhotoEntity> photoEntities, McUserEntity authon) {
        ArrayList<McPhotoEntity> toRemove = new ArrayList<>();
        for (McPhotoEntity photoEntity : photoEntities) {
            if (photoEntity.getUserByUsername() != authon) {
                toRemove.add(photoEntity);
            }
        }
        photoEntities.removeAll(toRemove);
        return photoEntities;
    }

}
