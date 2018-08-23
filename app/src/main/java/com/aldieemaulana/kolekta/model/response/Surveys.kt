package com.aldieemaulana.kolekta.model.response

import android.os.Parcel
import android.os.Parcelable
import com.aldieemaulana.kolekta.model.Survey

/**
 * Created by Al on 01/07/2018 for Kolekta
 */

data class Surveys(
    val message: String?,
    val status: Int?,
    val data: List<Survey>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.createTypedArrayList(Survey))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
        parcel.writeValue(status)
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Surveys> {
        override fun createFromParcel(parcel: Parcel): Surveys {
            return Surveys(parcel)
        }

        override fun newArray(size: Int): Array<Surveys?> {
            return arrayOfNulls(size)
        }
    }
}