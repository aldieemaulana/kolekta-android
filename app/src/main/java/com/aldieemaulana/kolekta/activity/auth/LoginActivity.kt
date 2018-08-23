package com.aldieemaulana.kolekta.activity.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.MainActivity
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.utils.Dialogs
import com.aldieemaulana.kolekta.utils.Texts
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListener()
    }

    private fun setListener() {
        buttonProceed.setOnClickListener {
            doLogin()
        }

        buttonForgotPassword.setOnClickListener {
            startActivity(Intent(context, ForgotPasswordActivity::class.java))
        }

        buttonRegister.setOnClickListener {
            startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    private fun doLogin() {
        val email: String = textEmail.text.toString()
        val password: String = textPassword.text.toString()

        Texts().shakeTextView(textError, context)

        if (email.isEmpty() && password.isEmpty()) {
            textError.text = getText(R.string.error_empty_password_n_email)
        } else if (email.isEmpty() && !password.isEmpty()) {
            textError.text = getText(R.string.error_empty_email)
        } else if (!email.isEmpty() && password.isEmpty()) {
            textError.text = getText(R.string.error_empty_password)
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6) {
                textError.text = getText(R.string.error_invalid_email)
            } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length <= 6) {
                textError.text = getText(R.string.error_invalid_password)
            } else {
                textError.text = ""
                callMain()
            }
        }
    }

    private fun callMain() {
        startActivity(Intent(context, MainActivity::class.java))
        finish()
    }

}

