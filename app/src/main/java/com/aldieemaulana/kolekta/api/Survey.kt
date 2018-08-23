package com.aldieemaulana.kolekta.api

import com.aldieemaulana.kolekta.model.response.Surveys
import com.aldieemaulana.kolekta.utils.Networks
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

/**
 * Created by Al on 01/07/2018 for Kolekta
 */

interface Survey {

    @GET("survey/list")
    @Headers("Content-Type: application/json")
    fun surveys(@Header("Authorization") token: String): Observable<Surveys>

    companion object {
        fun create(): Survey {
            return Networks().retrofit.create(Survey::class.java)
        }
    }

}