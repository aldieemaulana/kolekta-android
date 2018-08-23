package com.aldieemaulana.kolekta.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by Al on 24/06/2018 for Kolekta
 */

data class Survey(
    val id: Int?,
    val user: String?,
    val name: String?,
    val description: String?,
    @SerializedName("open_time")
    val openTime: String?,
    @SerializedName("close_time")
    val closeTime: String?,
    @SerializedName("required_asterik")
    val requiredAsterik: String?,
    @SerializedName("question_number")
    val questionNumber: String?,
    val logo: String?,
    val public: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    val pages: List<Page?>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Page))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(user)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(openTime)
        parcel.writeString(closeTime)
        parcel.writeString(requiredAsterik)
        parcel.writeString(questionNumber)
        parcel.writeString(logo)
        parcel.writeString(public)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeTypedList(pages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Survey> {
        override fun createFromParcel(parcel: Parcel): Survey {
            return Survey(parcel)
        }

        override fun newArray(size: Int): Array<Survey?> {
            return arrayOfNulls(size)
        }
    }
}
