
package com.ybdevelopers.demoproject.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feature {

    @SerializedName("feature_id")
    @Expose
    private String featureId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
