package com.ybdevelopers.mobilefeature.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MobileFeature() : Parcelable {

    @SerializedName("features")
    @Expose
    private var features: List<Feature?>? = null

    @SerializedName("exclusions")
    @Expose
    private var exclusions: List<List<Exclusion?>?>? = null

    constructor(parcel: Parcel) : this() {
        features = parcel.createTypedArrayList(Feature.CREATOR)
    }

    open fun getFeatures(): List<Feature?>? {
        return features
    }

    open fun setFeatures(features: List<Feature?>?) {
        this.features = features
    }

    open fun getExclusions(): List<List<Exclusion?>?>? {
        return exclusions
    }

    open fun setExclusions(exclusions: List<List<Exclusion?>?>?) {
        this.exclusions = exclusions
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(features)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MobileFeature> {
        override fun createFromParcel(parcel: Parcel): MobileFeature {
            return MobileFeature(parcel)
        }

        override fun newArray(size: Int): Array<MobileFeature?> {
            return arrayOfNulls(size)
        }
    }

}
