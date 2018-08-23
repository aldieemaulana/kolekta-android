package com.aldieemaulana.kolekta.utils

import android.content.Context

/**
 * Created by Al on 29/06/2018 for Kolekta
 */

open class Dimens {

    fun pxFromDp(dp: Int, context: Context): Float {
        return dp * context.resources.displayMetrics.density
    }

    fun dpFromPx(dp: Int, context: Context): Float {
        return dp / context.resources.displayMetrics.density
    }

}