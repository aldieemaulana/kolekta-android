package com.aldieemaulana.kolekta.activity.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.aldieemaulana.kolekta.R.string.text_failure_server_description
import com.aldieemaulana.kolekta.R.string.text_failure_server_title
import com.aldieemaulana.kolekta.utils.Dialogs
import com.aldieemaulana.kolekta.utils.Networks
import com.aldieemaulana.kolekta.view.AmBottomDialog
import com.kaopiz.kprogresshud.KProgressHUD
import io.reactivex.disposables.Disposable

/**
 * Created by Al on 23/06/2018 for Kolekta
 */

open class BaseActivity : AppCompatActivity() {

    open val context : Context = this
    open var progress: KProgressHUD? = null
    open var bottomDialogServerError: AmBottomDialog? = null
    open var disposable : Disposable? = null

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }

    private fun initDialog() {
        progress = Dialogs().initProgressDialog(context)
        bottomDialogServerError = Dialogs().initBottomDialog(
            context,
            getString(text_failure_server_title),
            getString(text_failure_server_description),
            false
        )
    }

    protected fun checkNetwork() {
        if(!Networks().isConnectedToServer()) {
            bottomDialogServerError?.show()
        }
    }


}