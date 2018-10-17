package com.aldieemaulana.kolekta

import android.app.Application

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class App : Application() {

    companion object {
        var TOKEN  = "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2tvbGVrdGFcL3B1YmxpY1wvYXBpXC92MlwvYXV0aFwvbG9naW4iLCJpYXQiOjE1MzUwMzI1NjIsImV4cCI6MTUzNTAzNjE2MiwibmJmIjoxNTM1MDMyNTYyLCJqdGkiOiJCc2hXaURvZk1nZ05EZkUzIiwic3ViIjo0LCJwcnYiOiJhYWM3MDJhNTc2YThiYjliMDNkZmQyZDYwYjM0YThlNjYwN2UxZDliIn0.IeJqMUbU8-9cHf7zknGD2SMJGy0d4XFMKCB-FxYO29U"
        var API = "http://192.168.60.150/kolekta/public/api/v2/"
        var URL = "http://192.168.60.150/kolekta/public/"
    }

}