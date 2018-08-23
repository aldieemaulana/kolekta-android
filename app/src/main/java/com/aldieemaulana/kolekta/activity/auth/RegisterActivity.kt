package com.aldieemaulana.kolekta.activity.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.utils.Dialogs
import com.aldieemaulana.kolekta.utils.Texts
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar_secondary.*

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setInit()
        setListener()
    }

    private fun setInit() {
        setToolbar()
    }

    private fun setListener() {
        buttonJoin.setOnClickListener {
            doRegister()
        }
    }

    private fun setToolbar() {
        textTitleToolbar.visibility = View.GONE
        buttonBackToolbar.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun doRegister() {
        val name: String = textFullName.text.toString()
        val username: String = textUsername.text.toString()
        val email: String = textEmail.text.toString()
        val password: String = textPassword.text.toString()
        val category: String = textCategory.selectedItem.toString()

        Texts().shakeTextView(textError, context)

        if (email.isEmpty() && username.isEmpty() && name.isEmpty() && password.isEmpty()) {
            setError(getText(R.string.error_empty_field) as String)
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6) {
                setError(getText(R.string.error_invalid_email) as String)
            } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length <= 6) {
                setError(getText(R.string.error_invalid_password) as String)
            } else {
                textError.text = ""
                progress?.show()
            }
        }
    }

    private fun setError(text: String) {
        textError.text  = text
        Texts().shakeTextView(textError, context)
    }

}
