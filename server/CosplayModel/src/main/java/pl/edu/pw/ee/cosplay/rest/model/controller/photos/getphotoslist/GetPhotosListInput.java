package pl.edu.pw.ee.cosplay.rest.model.controller.photos.getphotoslist;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by Micha� on 2016-05-20.
 */
public class GetPhotosListInput implements Serializable {

    private HashSet<String> filtrByFranchiseList = new HashSet<>();

    private HashSet<String> filtrByCharactersList = new HashSet<>();

    private PhotosOrder order;

    //range okre�laj� w jakim zakrecie <first; last>
    //czyli <1,5> zwr�ci dok�adnie pi�� pierwszych wynik�w
    private Integer rangeFirst;

    private Integer rangeLast;

    //jak null to nie uwzgl�dniamy
    //jak nie null to zwracamy list� przefiltrowan� przez
    //u�ytkownik�w obserwowanych (observed) przez obserwuj�cego (observer)
    private String observer; //

    //jak null to nie uwzgl�dniamy
    //jak nie null to filtrujemy zdj�cia wedle autora (username)
    private String author;

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

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
