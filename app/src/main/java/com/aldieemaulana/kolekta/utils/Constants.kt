package com.aldieemaulana.kolekta.utils

/**
 * Created by Al on 24/06/2018 for Kolekta
 */

open class Constants {
    companion object{
        const val ASSIGNED = "assigned"
        const val FINISHED = "finished"
        const val LIST_TYPE = "listType"
        const val SURVEY = "surveyData"
        const val AUTO_HIDE_ERROR = 6000
    }

    object INPUT {
        const val TEXT = "text"
        const val EMAIL = "text/email"
        const val RADIO = "radio"
    }

    object TYPE {
        const val EDIT = 1
        const val TEXT = 2
    }
}