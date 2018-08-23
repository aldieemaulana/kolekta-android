package com.aldieemaulana.kolekta.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.aldieemaulana.kolekta.App
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.adapter.card.SurveyCardAdapter
import com.aldieemaulana.kolekta.logger.AlLog
import com.aldieemaulana.kolekta.model.Survey
import com.aldieemaulana.kolekta.utils.Anims
import com.aldieemaulana.kolekta.utils.Constants
import com.aldieemaulana.kolekta.utils.Texts
import com.aldieemaulana.kolekta.utils.barcode.BarcodeCaptureActivity
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.android.gms.vision.barcode.BarcodeDetector.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_primary.*
import com.aldieemaulana.kolekta.api.Survey as API

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class MainActivity : BaseActivity() {

    private val request = 20
    private var detector: BarcodeDetector? = null
    private val capture = 9001
    private val survey by lazy { API.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInit()
        setListener()
        //checkNetwork()
    }

    private fun setInit() {
        setToolbar()
        buttonSearch.isEnabled = false
        detector = Builder(context)
                .setBarcodeFormats(Barcode.ALL_FORMATS).build()
        listOfSurveys()
    }

    private fun setListener() {
        buttonScanBarcode.setOnClickListener {
            doScanBarcode()
        }

        buttonSeeAllAssigned.setOnClickListener {
            doSeeAll(Constants.ASSIGNED)
        }

        buttonSeeAllFinished.setOnClickListener {
            doSeeAll(Constants.FINISHED)
        }

        buttonRefreshAssigned.setOnClickListener {
            listOfSurveys()
        }

        buttonRefreshFinished.setOnClickListener {
            listOfSurveys()
        }

        textSurveyCode.onChange()
    }

    @SuppressLint("RtlHardcoded")
    private fun setToolbar() {
        textTitleToolbar.text = getString(R.string.app_name)
        textTitleToolbar.gravity = Gravity.LEFT
        buttonBackToolbar.visibility = View.GONE
        buttonActionToolbar.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun setError(msg: String) {
        textError.text = msg
        Texts().shakeTextView(textError, context)
        buttonSearch.isEnabled = false
    }


    private fun setEmpty(t: Int) {
        if(t == View.GONE) {
            Anims().fadeOut(emptyAssigned, context)
            Anims().fadeOut(emptyFinished, context)
        }else {
            Anims().fadeIn(emptyAssigned, context)
            Anims().fadeIn(emptyFinished, context)
        }
    }

    private fun initCard(data: MutableList<Survey> = arrayListOf()) {
        val adapter = SurveyCardAdapter(data, context)

        recyclerViewAssigned.layoutManager = LinearLayoutManager(context,
                LinearLayout.HORIZONTAL, false)
        recyclerViewAssigned.adapter = adapter
        recyclerViewAssigned.isNestedScrollingEnabled = false
        recyclerViewFinished.layoutManager = LinearLayoutManager(context,
                LinearLayout.HORIZONTAL, false)
        recyclerViewFinished.adapter = adapter
        recyclerViewFinished.isNestedScrollingEnabled = false

        if(adapter.itemCount > 0) {
            Anims().fadeIn(recyclerViewAssigned, context)
            Anims().fadeIn(recyclerViewFinished, context)
            setEmpty(View.GONE)
        }else {
            Anims().fadeOut(recyclerViewAssigned, context)
            Anims().fadeOut(recyclerViewFinished, context)
            setEmpty(View.VISIBLE)
        }

    }

    private fun loading(t: Int) {
        if(t == View.GONE) {
            Anims().fadeOut(loadingAssigned, context)
            Anims().fadeOut(loadingFinished, context)
        }else {
            Anims().fadeIn(loadingAssigned, context)
            Anims().fadeIn(loadingFinished, context)
        }
    }

    private fun listOfSurveys() {
        val cards = ArrayList<Survey>()

        loading(View.VISIBLE)
        setEmpty(View.GONE)
        disposable = survey.surveys(App.TOKEN)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                cards.addAll(result.data)
                initCard(cards)
                loading(View.GONE)
            },
            { error ->
                AlLog.e("${error.message}")
                loading(View.GONE)
                setEmpty(View.VISIBLE)

            }
        )
    }

    private fun doSeeAll(listType: String) {
        val intent = Intent(context, ListDetailActivity::class.java)
        intent.putExtra(Constants.LIST_TYPE, listType)
        startActivity(intent)
    }

    private fun doScanBarcode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission_group.CAMERA, Manifest.permission.CAMERA), request)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            request -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(context, BarcodeCaptureActivity::class.java)
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, true)
                startActivityForResult(intent, capture)
            } else {
                setError("Camera Permission Denied!")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == capture) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode: Barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject)
                    textSurveyCode.setText(barcode.displayValue)
                    buttonSearch.isEnabled = barcode.displayValue.isNotEmpty()
                }
            } else {
                setError("Invalid error!")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun AppCompatEditText.onChange() {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                buttonSearch.isEnabled = s!!.isNotEmpty()
            }
        })
    }

}