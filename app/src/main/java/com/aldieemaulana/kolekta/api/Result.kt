package com.aldieemaulana.kolekta.api

import com.aldieemaulana.kolekta.model.response.Response
import com.aldieemaulana.kolekta.model.request.Results
import com.aldieemaulana.kolekta.utils.Networks
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Al on 01/07/2018 for Kolekta
 */

interface Result {

    @POST("result/store/{id}")
    @Headers("Content-Type: application/json")
    fun store(@Header("Authorization") token: String, @Path("id") id: Int, @Body data: Results.Request): Observable<Response>

    companion object {
        fun create(): Result {
            return Networks().retrofit.create(Result::class.java)
        }
    }

}