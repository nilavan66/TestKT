package com.example.testkt.icon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.R

class IconAdapter(private val iconList: List<Icon>, private val viewMoreClickListener: ViewMoreClickListener) :
    RecyclerView.Adapter<IconAdapter.ViewHolder>() {
    private var showAllItems = false

    fun setShowAllItems(showAll: Boolean) {
        showAllItems = showAll
        notifyDataSetChanged()
    }

    interface ViewMoreClickListener {
        fun onViewMoreClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.icons, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position < itemCount - 1 || showAllItems) {
            val icon = iconList[position]
            holder.iconImage.setImageResource(icon.icon)
            holder.iconText.text = icon.title
        } else {
            holder.iconImage.setImageResource(R.drawable.arrow_down)
            holder.iconText.text = "More"
            holder.itemView.setOnClickListener {
                viewMoreClickListener.onViewMoreClick()
            }
        }


    }

    override fun getItemCount(): Int {

        return if (showAllItems) {
            iconList.size
        } else {
            Math.min(iconList.size, 11) + 1
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImage: ImageView = itemView.findViewById(R.id.icon)
        val iconText: TextView = itemView.findViewById(R.id.iconTitle)
    }


}