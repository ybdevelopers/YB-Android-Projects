package com.ybdevelopers.mobilefeature.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Exclusion : Parcelable {
    @SerializedName("feature_id")
    @Expose
    var featureId: String? = null

    @SerializedName("options_id")
    @Expose
    var optionsId: String? = null

    protected constructor(`in`: Parcel) {
        featureId =
            `in`.readValue(String::class.java.classLoader) as String?
        optionsId =
            `in`.readValue(String::class.java.classLoader) as String?
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(featureId)
        dest.writeValue(optionsId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Exclusion?> = object : Parcelable.Creator<Exclusion?> {
            override fun createFromParcel(`in`: Parcel): Exclusion? {
                return Exclusion(`in`)
            }

            override fun newArray(size: Int): Array<Exclusion?> {
                return arrayOfNulls(size)
            }
        }
    }
}