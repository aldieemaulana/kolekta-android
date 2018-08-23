package com.aldieemaulana.kolekta.adapter.card

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.activity.DetailActivity
import com.aldieemaulana.kolekta.activity.ListDetailActivity
import com.aldieemaulana.kolekta.logger.AlLog
import com.aldieemaulana.kolekta.model.Survey
import com.aldieemaulana.kolekta.utils.Constants
import com.aldieemaulana.kolekta.view.AmTextView
import kotlinx.android.synthetic.main.item_survey_card.view.*

/**
 * Created by Al on 24/06/2018 for Kolekta
 */

class SurveyCardAdapter(private val surveyList: MutableList<Survey>, private var context: Context) : RecyclerView.Adapter<SurveyCardAdapter.ViewHolder>() {

    private fun loadIntent(position: Int, context: Context) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(Constants.SURVEY, surveyList[position])
        context.startActivity(intent)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: AmTextView = itemView.textSurveyName
        val layoutParent: ConstraintLayout = itemView.parentLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_survey_card, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = surveyList[position].name
        holder.layoutParent.setOnClickListener{
            loadIntent(position, context)
        }
    }

    override fun getItemCount(): Int {
        return surveyList.size
    }

    fun clear() {
        surveyList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<Survey>) {
        surveyList.addAll(list)
        notifyDataSetChanged()
    }

    fun add(survey: Survey) {
        surveyList.add(survey)
        notifyDataSetChanged()
    }


}