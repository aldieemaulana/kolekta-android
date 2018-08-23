package com.aldieemaulana.kolekta.activity

import android.os.Bundle
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.base.BaseActivity
import com.aldieemaulana.kolekta.adapter.card.GroupCardAdapter
import com.aldieemaulana.kolekta.adapter.list.InfoListAdapter
import com.aldieemaulana.kolekta.model.Group
import com.aldieemaulana.kolekta.model.Info
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.toolbar_primary.*

/**
 * Created by Al on 20/05/17 for Kolekta
 */

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setInit()
        setListener()
    }

    private fun setInit() {
        setToolbar()
        initCard()
        initList()
    }

    private fun setListener() {

    }

    private fun setToolbar() {
        textTitleToolbar.text = getString(R.string.app_profile)
        buttonActionToolbar.setImageResource(R.drawable.ic_log_out)
        buttonBackToolbar.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun initCard() {
        val cards = ArrayList<Group>()
        cards.add(Group(1, "Employees Engagement"))
        cards.add(Group(2, "Market Research Product Testing"))
        cards.add(Group(3, "Net Promoter"))
        cards.add(Group(4, "Website Feedback"))

        val adapter = GroupCardAdapter(cards)
        recyclerViewGroup.adapter = adapter
    }

    private fun initList() {
        val infos = ArrayList<Info>()
        infos.add(Info(1, "Email", "aldieemaulana@gmail.com"))
        infos.add(Info(2, "Account Plan", "Basic"))
        infos.add(Info(3, "Phone Number", "+62 87770122245"))
        infos.add(Info(4, "Address", "Balittro No.4"))
        infos.add(Info(5, "Join on", "21-07-2016"))
        infos.add(Info(6, "Status", "Verified"))

        val adapter = InfoListAdapter(infos)
        recyclerViewBasic.adapter = adapter
    }

}
