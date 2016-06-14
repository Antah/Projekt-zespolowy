package pl.edu.pw.ee.cosplay.rest.model.controller.photos;

import java.io.Serializable;

/**
 * Created by Micha³ on 2016-05-20.
 */
public class RatingData implements Serializable {

    private Integer generalRate = 0, similarityRate = 0, qualityRate = 0, arrangementRate = 0;

    public Integer getGeneralRate() {
        return generalRate;
    }

    public void setGeneralRate(Integer generalRate) {
        this.generalRate = generalRate;
    }

    public Integer getSimilarityRate() {
        return similarityRate;
    }

    public void setSimilarityRate(Integer similarityRate) {
        this.similarityRate = similarityRate;
    }

    public Integer getQualityRate() {
        return qualityRate;
    }

    public void setQualityRate(Integer qualityRate) {
        this.qualityRate = qualityRate;
    }

    public Integer getArrangementRate() {
        return arrangementRate;
    }

    public void setArrangementRate(Integer arrangementRate) {
        this.arrangementRate = arrangementRate;
    }
}
