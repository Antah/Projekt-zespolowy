package pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Micha³ on 2016-05-20.
 */
public class GetPhotosListInput implements Serializable{

    private HashSet<String> filtrByFranchiseList;

    private HashSet<String> filtrByCharactersList;

    private PhotosOrder order;

    //range okreœlaj¹ w jakim zakrecie <first; last> maj¹ byæ zwrócone wyniki
    private Integer rangeFirst;

    private Integer rangeLast;

    public HashSet<String> getFiltrByFranchiseList() {
        return filtrByFranchiseList;
    }

    public void setFiltrByFranchiseList(HashSet<String> filtrByFranchiseList) {
        this.filtrByFranchiseList = filtrByFranchiseList;
    }

    public HashSet<String> getFiltrByCharactersList() {
        return filtrByCharactersList;
    }

    public void setFiltrByCharactersList(HashSet<String> filtrByCharactersList) {
        this.filtrByCharactersList = filtrByCharactersList;
    }

    public PhotosOrder getOrder() {
        return order;
    }

    public void setOrder(PhotosOrder order) {
        this.order = order;
    }

    public Integer getRangeFirst() {
        return rangeFirst;
    }

    public void setRangeFirst(Integer rangeFirst) {
        this.rangeFirst = rangeFirst;
    }

    public Integer getRangeLast() {
        return rangeLast;
    }

    public void setRangeLast(Integer rangeLast) {
        this.rangeLast = rangeLast;
    }
}
