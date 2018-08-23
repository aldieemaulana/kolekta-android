package com.aldieemaulana.kolekta.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Al on 01/07/2018 for Kolekta
 */

data class Response (
    @SerializedName("message") val message: String = "",
    @SerializedName("status") val status: Int = 0,
    @SerializedName("data") val data: Any
)