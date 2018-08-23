package com.aldieemaulana.kolekta.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.logger.AlLog
import com.aldieemaulana.kolekta.utils.Dialogs
import com.aldieemaulana.kolekta.utils.Utils

/**
 * Created by Al on 26/06/2018
 */

class AmLinearLayout : android.support.v7.widget.LinearLayoutCompat {

    private var mEditRes: Int = 0
    private var mDialog: Boolean = false
    private var mDialogQuestion: String = ""
    private var mDialogList: List<String> = listOf()

    private lateinit var mEdit: View

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setValues(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setValues(attrs)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setOnClickFocus()
    }

    fun setDialogList(list: HashMap<String, Int>) {
        mDialogList = list.keys.distinct()
    }

    fun getDialogList() : List<String> {
        return mDialogList
    }

    fun setDialogQuestion(question: String) {
        mDialogQuestion = question
    }

    fun setDialog(state: Boolean) {
        mDialog = state
    }

    fun getDialog() : Boolean {
        return mDialog
    }

    fun setEditText(editText: View) {
        mEdit = editText
    }

    private fun setOnClickFocus() {
        if(mEditRes > 0 && mEditRes != -1) {
            mEdit = (parent as View).findViewById(mEditRes)!!

            setOnClickListener{
                if(!mDialog) {
                    mEdit.requestFocus()
                    Utils().showKeyboard(context)
                }else{
                    if(mDialogList.isNotEmpty()) {
                        (mEdit as AmTextView).hideError()
                        Dialogs().spinnerDialog(context, mDialogList, mDialogQuestion, mEdit as AmTextView, false)
                    }
                }
            }

        }
    }

    fun init(list: HashMap<String, Int>, state: Boolean, component: AmTextView, question: String) {
        setDialogList(list)
        setDialog(state)
        setEditText(component)
        setDialogQuestion(question)

        setOnClickListener {
            if (mDialogList.isNotEmpty()) {
                (mEdit as AmTextView).hideError()
                Dialogs().spinnerDialog(context, mDialogList, mDialogQuestion, mEdit as AmTextView, true)
            }
        }
    }

    @SuppressLint("CustomViewStyleable")
    private fun setValues(attrs: AttributeSet?) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.AmView)
        try {
            val n = attr.indexCount
            for (i in 0 until n) {
                val attribute = attr.getIndex(i)
                when (attribute) {
                    R.styleable.AmView_Amedit -> mEditRes = attr.getResourceId(R.styleable.AmView_Amedit, -1)
                    R.styleable.AmView_Amdialog -> mDialog = attr.getBoolean(R.styleable.AmView_Amdialog, false)
                    else -> AlLog.d("Unknown attribute for " + javaClass.toString() + ": " + attribute)
                }
            }
        } finally {
            attr.recycle()
        }
    }

}

