package com.aldieemaulana.kolekta.adapter.card

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.aldieemaulana.kolekta.R
import com.aldieemaulana.kolekta.model.Group
import com.aldieemaulana.kolekta.view.AmTextView
import kotlinx.android.synthetic.main.item_group_card.view.*

/**
 * Created by Al on 24/06/2018 for Kolekta
 */

class GroupCardAdapter(private val GroupList: MutableList<Group>) : RecyclerView.Adapter<GroupCardAdapter.ViewHolder>() {

    private fun loadIntet(position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: AmTextView = itemView.textFullName
        val layoutParent: ConstraintLayout = itemView.parentLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_group_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = GroupList[position].name
        holder.layoutParent.setOnClickListener{
            loadIntet(position)
        }
    }

    override fun getItemCount(): Int {
        return GroupList.size
    }

    fun clear() {
        GroupList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<Group>) {
        GroupList.addAll(list)
        notifyDataSetChanged()
    }

    fun add(Group: Group) {
        GroupList.add(Group)
        notifyDataSetChanged()
    }


}