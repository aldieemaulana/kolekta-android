package com.aldieemaulana.kolekta.activity

import android.os.Bundle
import android.text.InputType
import android.view.View
import com.aldieemaulana.kolekta.App
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.api.Result
import com.aldieemaulana.kolekta.logger.AlLog
import com.aldieemaulana.kolekta.model.Answer
import com.aldieemaulana.kolekta.model.Page
import com.aldieemaulana.kolekta.model.Question
import com.aldieemaulana.kolekta.model.Survey
import com.aldieemaulana.kolekta.model.request.Results
import com.aldieemaulana.kolekta.utils.Anims
import com.aldieemaulana.kolekta.utils.Constants
import com.aldieemaulana.kolekta.utils.Texts
import com.aldieemaulana.kolekta.utils.Views
import com.aldieemaulana.kolekta.view.AmEditText
import com.aldieemaulana.kolekta.view.AmTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_primary.*

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class DetailActivity : BaseActivity() {

    private lateinit var data : Survey
    private var inputs : HashMap<Int, Int> = hashMapOf()
    private var answers : HashMap<Int, HashMap<String, Int>> = hashMapOf()
    private var questions : HashMap<Int, Question> = hashMapOf()
    private val result by lazy { Result.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setInit()
        setListener()
    }

    private fun setInit() {
        data = intent.getParcelableExtra(Constants.SURVEY)

        setToolbar()
        setInitInput()
    }

    private fun setInitInput() {
        val pages = data.pages

        pages?.forEach { page: Page? ->
            if (page != null) {
                Views().title(context, page.name, page.description, fieldLayout, when (page.position) {
                    1 -> true
                    else -> false
                })

                page.questions?.forEach { question: Question? ->
                    if (question != null) {
                        val type = question.typed?.name
                        val textQuestion = question.question!! + when (question.required) {
                            "1" -> " *"
                            else -> ""
                        }

                        val id = question.id!!

                        questions[id] = question

                        when (type) {
                            Constants.INPUT.TEXT -> {
                                question.answers?.get(0)?.answer?.let { hint -> Views().editText(context, id, InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE, textQuestion, hint, fieldLayout) }
                                this.inputs[id] = Constants.TYPE.EDIT
                            }
                            Constants.INPUT.EMAIL -> {
                                question.answers?.get(0)?.answer?.let { hint -> Views().editText(context, id, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, textQuestion, hint, fieldLayout) }
                                this.inputs[id] = Constants.TYPE.EDIT
                            }
                            Constants.INPUT.RADIO -> {
                                val options : HashMap<String, Int> = hashMapOf()

                                question.answers?.forEach { answer: Answer? ->
                                    val textAnswer = answer?.answer!!
                                    options[textAnswer] = answer.id!!
                                }

                                question.answers?.get(0)?.answer?.let { hint -> Views().radio(context, id, textQuestion, hint, options, fieldLayout) }
                                this.inputs[id] = Constants.TYPE.TEXT
                                this.answers[id] = options
                            }
                        }
                    }
                }
            }


        }

    }

    private fun setListener() {
        buttonSave.setOnClickListener {
            var validate = true
            val surveyId = data.id!!
            val results : MutableList<Results.Result> = mutableListOf()

            this.inputs.forEach {
                val question = this.questions[it.key]

                when(it.value) {
                    Constants.TYPE.TEXT -> {
                        val component : AmTextView = findViewById(it.key)
                        val selected = component.text.toString()

                        if(question?.required!! == "1" && selected.isEmpty()) {
                            component.showError("Please fill this text")
                            validate = false
                        }else {
                            val answer = this.answers[component.id]
                            results.add(Results.Result(question.id!!, answer!![selected].toString()))
                        }
                    }

                    Constants.TYPE.EDIT -> {
                        val component : AmEditText = findViewById(it.key)
                        val answer = component.text.toString()

                        if(question?.required!! == "1" && answer.isEmpty()) {
                            component.showError("Don't leave it blank")
                            validate = false
                        }else{
                            results.add(Results.Result(question.id!!, answer))
                        }
                    }
                }
            }

            val resultData = Results.Request(surveyId, results)

            if(validate) {
                setLoading(true)
                disposable = result.store(App.TOKEN, surveyId, resultData)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    AlLog.e("${result.data}")
                                    setLoading(false)
                                },
                                { error ->
                                    AlLog.e("${error.message}")
                                    setLoading(false)

                                }
                        )
            }
        }

    }

    private fun setLoading(state: Boolean) {
        if(state) {
            progressLayout.visibility = View.VISIBLE
        }else{
            progressLayout.visibility = View.GONE
        }
    }

    private fun setToolbar() {
        textTitleToolbar.text = data.name?.capitalize() ?: "Detail"
        buttonActionToolbar.visibility = View.GONE
        buttonBackToolbar.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setError(text: String) {
        textError.text  = text
        Texts().shakeTextView(textError, context)
        setLoading(false)
    }


}
