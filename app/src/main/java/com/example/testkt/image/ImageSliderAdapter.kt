package com.example.testkt.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.R

class ImageSliderAdapter (private val imageList: List<ImageItem>)
    : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>(){

        inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val imageView : ImageView = itemView.findViewById(R.id.item)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false))
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = imageList[position]
        holder.imageView.setImageResource(currentItem.imageResourceId)
    }


    override fun getItemCount(): Int {
        return imageList.size
    }
}