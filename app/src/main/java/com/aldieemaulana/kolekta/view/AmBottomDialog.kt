package com.aldieemaulana.kolekta.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.View
import com.aldieemaulana.kolekta.R
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.am_bottom_sheet.*


/**
 * Created by Al on 07/07/2018 for Kolekta
 */

class AmBottomDialog(context: Context) : BottomSheetDialog(context), View.OnClickListener {

    override fun onClick(v: View?) {
        TODO("not implemented")
    }

    init {
        create()
    }

    override fun create() {
        val bottomSheetView = layoutInflater.inflate(R.layout.am_bottom_sheet, null)
        setContentView(bottomSheetView)
    }

    fun title(text: String) {
        textTitle.text = text
    }

    fun description(text: String) {
        textDescription.text = text
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private val instance: AmBottomDialog? = null

        fun getInstance(@NonNull context: Context): AmBottomDialog {
            return instance ?: AmBottomDialog(context)
        }
    }
}