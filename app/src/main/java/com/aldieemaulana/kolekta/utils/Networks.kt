package com.aldieemaulana.kolekta.utils

import com.aldieemaulana.kolekta.App
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL


/**
 * Created by Al on 06/07/2018 for Kolekta
 */

open class Networks {
/*
    var restAdapter = RestAdapter.Builder()
            .setEndpoint(App.API)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setErrorHandler(CustomErrorHandler(ctx))  // use error handler..
            .build()*/



    val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(App.API)
            .build()

    fun isConnectedToServer(): Boolean {
        val uri = App.URL

        return try {

            val url = URL(uri)
            val conn = url.openConnection()
            conn.readTimeout = 100000
            conn.connectTimeout = 150000
            conn.connect()
            true
        } catch (e: Exception) {
            false
        }
    }

}