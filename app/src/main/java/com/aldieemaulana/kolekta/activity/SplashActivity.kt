package com.aldieemaulana.kolekta.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler(100)
    }

    private fun handler(duration: Long) {
        val mHandler = Handler()
        val mRunnable = Runnable {
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        }

        mHandler.postDelayed(mRunnable, duration)
    }
}
