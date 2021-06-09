
package com.ybdevelopers.demoproject.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileListing {

    @SerializedName("features")
    @Expose
    private List<Feature> features = null;
    @SerializedName("exclusions")
    @Expose
    private List<List<Exclusion>> exclusions = null;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<List<Exclusion>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<List<Exclusion>> exclusions) {
        this.exclusions = exclusions;
    }

}
