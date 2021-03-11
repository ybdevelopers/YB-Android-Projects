
package com.ybdevelopers.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLocationData implements Parcelable
{

    @SerializedName("success")
    @Expose
    private List<Success> success = new ArrayList<>();
    @SerializedName("location")
    @Expose
    private List<Location> location = new ArrayList<>();
    public final static Creator<GetLocationData> CREATOR = new Creator<GetLocationData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetLocationData createFromParcel(Parcel in) {
            return new GetLocationData(in);
        }

        public GetLocationData[] newArray(int size) {
            return (new GetLocationData[size]);
        }

    }
    ;

    protected GetLocationData(Parcel in) {
        in.readList(this.success, (Success.class.getClassLoader()));
        in.readList(this.location, (Location.class.getClassLoader()));
    }

    public GetLocationData() {
    }

    public List<Success> getSuccess() {
        return success;
    }

    public void setSuccess(List<Success> success) {
        this.success = success;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(success);
        dest.writeList(location);
    }

    public int describeContents() {
        return  0;
    }

}
