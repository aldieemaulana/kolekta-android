package com.aldieemaulana.kolekta.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Al on 01/07/2018 for Kolekta
 */

object Results {
    data class Request(
        @SerializedName("survey") val survey: Int,
        @SerializedName("results") val data: List<Result> = listOf()
    )

    data class Result(
        @SerializedName("question") val question: Int,
        @SerializedName("result") val answer: String = ""
    )
}