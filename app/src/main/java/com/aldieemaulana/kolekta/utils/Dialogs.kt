package com.aldieemaulana.kolekta.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.adapter.list.DialogListAdapter
import com.aldieemaulana.kolekta.view.AmBottomDialog
import com.aldieemaulana.kolekta.view.AmTextView
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.am_bottom_sheet.*
import kotlinx.android.synthetic.main.dialog_spinner.view.*
import android.view.Gravity
import android.view.View

/**
 * Created by Al on 24/06/2018 for Kolekta
 */

open class Dialogs {

    fun initProgressDialog(context: Context): KProgressHUD {
        return KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setDimAmount(0.5f)
            .setAnimationSpeed(2)
    }

    fun initBottomDialog(context: Context, title: String, description: String, cancelable: Boolean): AmBottomDialog {
        val amBottom = AmBottomDialog.getInstance(context).apply {
            title(title)
            description(description)
            setCanceledOnTouchOutside(cancelable)
        }

        amBottom.buttonRefresh.setOnClickListener {
            BaseActivity().progress?.show()
            val networkState = Networks().isConnectedToServer()
            if(networkState)
                amBottom.hide()

            BaseActivity().progress?.dismiss()
        }

        return amBottom
    }

    @SuppressLint("InflateParams")
    fun spinnerDialog(context: Context, lists: List<String>, title: String, component: AmTextView, cancelledOnTouch: Boolean) {

        val dialog = Dialog(context, R.style.AppTheme_Dialog)
        val layoutInflater = LayoutInflater.from(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_spinner, null)
        val dialogListAdapter = DialogListAdapter(lists, context, dialog, component)

        if (title.isNotEmpty()) {
            dialogView.textTitle.text = title
            dialogView.textTitle.visibility = View.VISIBLE
        }

        if(component.text.toString().isNotEmpty()) {
            dialogListAdapter.selected(component.text.toString())
        }

        dialogView.listViewDialog.adapter = dialogListAdapter
        dialog.window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.setContentView(dialogView)
        dialog.setCanceledOnTouchOutside(cancelledOnTouch)
        dialog.show()

    }

}