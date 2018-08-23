package com.aldieemaulana.kolekta.utils

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.aldieemaulana.kolekta.R
import android.view.animation.Animation
import android.animation.ValueAnimator
import com.aldieemaulana.kolekta.view.AmTextView


/**
 * Created by Al on 24/06/2018 for Kolekta
 */

open class Anims {

    fun shaked(mTextView: View, context: Context) {
        val animShake = AnimationUtils.loadAnimation(context, R.anim.shake)
        mTextView.startAnimation(animShake)
    }

    fun fadeOut(mTextView: View, context: Context) {
        val animFadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        animFadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                mTextView.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        mTextView.startAnimation(animFadeOut)
    }

    fun fadeIn(mTextView: View, context: Context) {
        val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animFadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                mTextView.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
        })
        mTextView.startAnimation(animFadeIn)

    }


}