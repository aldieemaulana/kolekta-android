package com.aldieemaulana.kolekta.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.adapter.list.SurveyListAdapter
import com.aldieemaulana.kolekta.model.Survey
import com.aldieemaulana.kolekta.utils.Constants
import kotlinx.android.synthetic.main.activity_list_detail.*
import kotlinx.android.synthetic.main.toolbar_primary.*

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class ListDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        setInit()
        setListener()
    }

    private fun setInit() {
        setToolbar()
        initList()
    }

    private fun setListener() {

    }

    private fun setToolbar() {
        textTitleToolbar.text = intent.getStringExtra(Constants.LIST_TYPE).capitalize()
        buttonActionToolbar.setImageResource(R.drawable.ic_search)
        buttonBackToolbar.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun initList() {
        /*val cards = ArrayList<Survey>()
        cards.add(Survey(1, "Employees Engagement"))
        cards.add(Survey(2, "Market Research Product Testing"))
        cards.add(Survey(3, "Net Promoter"))
        cards.add(Survey(4, "Website Feedback"))

        var adapter = SurveyListAdapter(cards)

        recyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayout.VERTICAL, false)
        recyclerView.adapter = adapter*/

    }

}
