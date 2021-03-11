
package com.ybdevelopers.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("longg")
    @Expose
    private Double longg;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    public final static Creator<Location> CREATOR = new Creator<Location>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    }
    ;

    protected Location(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.longg = ((Double) in.readValue((Double.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Location() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongg() {
        return longg;
    }

    public void setLongg(Double longg) {
        this.longg = longg;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(code);
        dest.writeValue(lat);
        dest.writeValue(longg);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return  0;
    }

}
