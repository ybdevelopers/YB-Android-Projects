package com.ybdevelopers.mobilefeature.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Option : Parcelable {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    protected constructor(`in`: Parcel) {
        name = `in`.readValue(String::class.java.classLoader) as String?
        icon = `in`.readValue(String::class.java.classLoader) as String?
        id = `in`.readValue(String::class.java.classLoader) as String?
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(name)
        dest.writeValue(icon)
        dest.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Option?> = object : Parcelable.Creator<Option?> {
            override fun createFromParcel(`in`: Parcel): Option? {
                return Option(`in`)
            }

            override fun newArray(size: Int): Array<Option?> {
                return arrayOfNulls(size)
            }
        }
    }
}
