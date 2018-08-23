package com.aldieemaulana.kolekta.activity.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.utils.Dialogs
import com.aldieemaulana.kolekta.utils.Texts
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.toolbar_secondary.*

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class ForgotPasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        setInit()
        setListener()
    }

    private fun setInit() {
        setToolbar()
    }

    private fun setListener() {
        buttonResetPassword.setOnClickListener {
            doResetPassword()
        }
    }

    private fun setToolbar() {
        textTitleToolbar.visibility = View.GONE
        buttonBackToolbar.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun doResetPassword() {
        val email: String = textEmail.text.toString()

        Texts().shakeTextView(textError, context)

        if (email.isEmpty()) {
            textError.text = getText(R.string.error_empty_email)
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textError.text = getText(R.string.error_invalid_email)
            } else {
                textError.text = ""
                progress?.show()
            }
        }
    }

}
