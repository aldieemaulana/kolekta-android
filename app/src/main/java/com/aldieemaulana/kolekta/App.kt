package com.aldieemaulana.kolekta

import android.app.Application

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class App : Application() {

    companion object {
        var TOKEN  = "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2tvbGVrdGFcL3B1YmxpY1wvYXBpXC92MlwvYXV0aFwvbG9naW4iLCJpYXQiOjE1MzQ5NTc3MTQsImV4cCI6MTUzNDk2MTMxNCwibmJmIjoxNTM0OTU3NzE0LCJqdGkiOiJhblFsS2htakdRblIycEtqIiwic3ViIjo0LCJwcnYiOiJhYWM3MDJhNTc2YThiYjliMDNkZmQyZDYwYjM0YThlNjYwN2UxZDliIn0.V9W75ptmzOTi28oIchH18HqxqSo9N7jKITPhJA_q-7U"
        var API = "http://172.20.10.14/kolekta/public/api/v2/"
        var URL = "http://172.20.10.14/kolekta/public/"
    }

}