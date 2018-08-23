package com.aldieemaulana.kolekta.adapter.list

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.model.Info
import kotlinx.android.synthetic.main.item_info_list.view.*

/**
 * Created by Al on 24/06/2018 for Kolekta
 */

class InfoListAdapter(private val infoList: MutableList<Info>) : RecyclerView.Adapter<InfoListAdapter.ViewHolder>() {

    private fun loadIntet(position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: AppCompatTextView = itemView.textTitle
        val description: AppCompatTextView = itemView.textValue
        val layoutParent: ConstraintLayout = itemView.parentLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_info_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = infoList[position].title
        holder.description.text = infoList[position].value
        holder.layoutParent.setOnClickListener{
            loadIntet(position)
        }
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    fun clear() {
        infoList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<Info>) {
        infoList.addAll(list)
        notifyDataSetChanged()
    }

    fun add(survey: Info) {
        infoList.add(survey)
        notifyDataSetChanged()
    }


}