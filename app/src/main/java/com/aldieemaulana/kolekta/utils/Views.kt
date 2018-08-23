package com.aldieemaulana.kolekta.utils

import android.content.Context
import android.os.Build
import android.widget.LinearLayout
import com.aldieemaulana.kolekta.R
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.ContextThemeWrapper
import android.view.View
import com.aldieemaulana.kolekta.view.AmEditText
import com.aldieemaulana.kolekta.view.AmLinearLayout
import com.aldieemaulana.kolekta.view.AmTextView


/**
 * Created by Al on 01/07/2018 for Kolekta
 */

open class Views {

    fun editText(context: Context, id: Int, inputType: Int, text: String, hint: String, parent: LinearLayout) {
        val linearLayout = LinearLayout(ContextThemeWrapper(context, R.style.AppTheme_WrappingInput), null, 0)
        val linearLayoutLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            linearLayout.background = context.getDrawable(R.drawable.input_primary_flat)
        }else{
            linearLayout.setBackgroundDrawable(context.resources.getDrawable(R.drawable.input_primary_flat))
        }

        linearLayoutLayoutParams.bottomMargin = context.resources.getDimension(R.dimen.component_big).toInt()
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearLayoutLayoutParams

        val textViewLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        val textViewError = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextLabelError), null, 0)
        textViewError.layoutParams = textViewLayoutParams
        textViewError.requestLayout()
        textViewError.setEditText(id)
        textViewError.setFont("B")

        val textView = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextLabelInput), null, 0)
        textView.text = text
        textView.layoutParams = textViewLayoutParams
        textView.requestLayout()
        textView.setEditText(id)
        textView.setTextColor(context.resources.getColor(R.color.colorTextPrimary))
        textView.setFont("M")

        val editText = AmEditText(ContextThemeWrapper(context, R.style.AppTheme_TextInput), null, 0)
        editText.id = id
        editText.hint = hint
        editText.setErrorEdit(textViewError)
        editText.layoutParams = textViewLayoutParams
        editText.requestLayout()
        editText.setFont("R")
        editText.inputType = inputType

        if(inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            editText.transformationMethod = PasswordTransformationMethod.getInstance()

        linearLayout.addView(textView)
        linearLayout.addView(editText)
        linearLayout.addView(textViewError)

        parent.addView(linearLayout)

        textView.init(editText)


    }

    fun radio(context: Context, id: Int, text: String, hint: String, answers: HashMap<String, Int>, parent: LinearLayout) {
        val textViewLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        val textView = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextLabelInput), null, 0)
        textView.text = text
        textView.layoutParams = textViewLayoutParams
        textView.setTextColor(context.resources.getColor(R.color.colorTextPrimary))
        textView.requestLayout()

        val textViewError = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextLabelError), null, 0)
        textViewError.layoutParams = textViewLayoutParams
        textViewError.requestLayout()

        val editText = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextInput), null, 0)
        editText.id = id
        editText.hint = hint
        editText.setErrorEdit(textViewError)
        editText.layoutParams = textViewLayoutParams
        editText.isFocusable = false
        editText.isClickable = false
        editText.requestLayout()

        val linearLayout = AmLinearLayout(ContextThemeWrapper(context, R.style.AppTheme_WrappingInput), null, 0)
        val linearLayoutLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            linearLayout.background = context.getDrawable(R.drawable.input_primary_flat)
        }else{
            linearLayout.setBackgroundDrawable(context.resources.getDrawable(R.drawable.input_primary_flat))
        }

        linearLayoutLayoutParams.bottomMargin = context.resources.getDimension(R.dimen.component_big).toInt()
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearLayoutLayoutParams

        linearLayout.addView(textView)
        linearLayout.addView(editText)
        linearLayout.addView(textViewError)

        parent.addView(linearLayout)

        linearLayout.init(answers, true, editText, text)
        textView.setFont("M")
        editText.setFont("R")
        textViewError.setFont("B")

    }

    fun title(context: Context, title: String?, description: String?, parent: LinearLayout, first: Boolean) {
        val textViewLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val textViewTitle = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextLabelTitle), null, 0)
        if(description.isNullOrEmpty())
            textViewLayoutParams.bottomMargin = context.resources.getDimension(R.dimen.component_big).toInt()
        else
            textViewLayoutParams.bottomMargin = context.resources.getDimension(R.dimen.component_extra_small).toInt()

        if(!first)
            textViewLayoutParams.topMargin = context.resources.getDimension(R.dimen.component_extra_big).toInt()

        textViewTitle.text = title
        textViewTitle.layoutParams = textViewLayoutParams
        textViewTitle.requestLayout()

        val textViewLayoutDescriptionParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val textViewDescription = AmTextView(ContextThemeWrapper(context, R.style.AppTheme_TextLabelDescription), null, 0)

        if(!description.isNullOrEmpty())
            textViewLayoutDescriptionParams.bottomMargin = context.resources.getDimension(R.dimen.component_big).toInt()
        else
            textViewDescription.visibility = View.GONE

        textViewDescription.text = description
        textViewDescription.layoutParams = textViewLayoutDescriptionParams
        textViewDescription.requestLayout()

        parent.addView(textViewTitle)
        parent.addView(textViewDescription)

        textViewDescription.setFont("R")
        textViewTitle.setFont("M")
    }

}