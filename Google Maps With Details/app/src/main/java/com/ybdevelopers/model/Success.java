
package com.ybdevelopers.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Success implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("categoryid")
    @Expose
    private Integer categoryid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("empcode")
    @Expose
    private String empcode;
    public final static Creator<Success> CREATOR = new Creator<Success>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Success createFromParcel(Parcel in) {
            return new Success(in);
        }

        public Success[] newArray(int size) {
            return (new Success[size]);
        }

    }
    ;

    protected Success(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.contact = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.empcode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Success() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(contact);
        dest.writeValue(categoryid);
        dest.writeValue(description);
        dest.writeValue(address);
        dest.writeValue(empcode);
    }

    public int describeContents() {
        return  0;
    }

}
