package com.aldieemaulana.kolekta.adapter.list

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.model.Survey
import kotlinx.android.synthetic.main.item_survey_list.view.*

/**
 * Created by Al on 24/06/2018 for Kolekta
 */

class SurveyListAdapter(private val cardList: MutableList<Survey>) : RecyclerView.Adapter<SurveyListAdapter.ViewHolder>() {

    private fun loadIntet(position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: AppCompatTextView = itemView.textSurveyName
        val layoutParent: ConstraintLayout = itemView.parentLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_survey_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = cardList[position].name
        holder.layoutParent.setOnClickListener{
            loadIntet(position)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun clear() {
        cardList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<Survey>) {
        cardList.addAll(list)
        notifyDataSetChanged()
    }

    fun add(survey: Survey) {
        cardList.add(survey)
        notifyDataSetChanged()
    }


}