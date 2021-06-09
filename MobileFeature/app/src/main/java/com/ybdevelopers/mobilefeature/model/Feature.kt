package com.ybdevelopers.mobilefeature.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Feature : Parcelable {
    @SerializedName("feature_id")
    @Expose
    var featureId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("options")
    @Expose
    var options: List<Option?>? = null

    protected constructor(`in`: Parcel) {
        featureId =
            `in`.readValue(String::class.java.classLoader) as String?
        name = `in`.readValue(String::class.java.classLoader) as String?
        `in`.readList(options!!, Option::class.java.classLoader)
    }

    constructor()

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(featureId)
        dest.writeValue(name)
        dest.writeList(options)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Feature?> = object : Parcelable.Creator<Feature?> {
            override fun createFromParcel(`in`: Parcel): Feature? {
                return Feature(`in`)
            }

            override fun newArray(size: Int): Array<Feature?> {
                return arrayOfNulls(size)
            }
        }
    }
}