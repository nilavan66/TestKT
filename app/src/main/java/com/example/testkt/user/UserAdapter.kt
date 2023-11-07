package com.example.testkt.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.testkt.R
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso


class UserAdapter(private var userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }


    override fun getItemCount(): Int {
        return userList.size
    }


    fun updateData(newData: List<User>) {
        userList = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val fname: MaterialTextView = itemView.findViewById(R.id.fname)
        private val lname: MaterialTextView = itemView.findViewById(R.id.lname)
        private val email: MaterialTextView = itemView.findViewById(R.id.email)
        private val imageView: ImageView = itemView.findViewById(R.id.image)


        fun bind(user: User) {
            fname.text = user.first_name
            lname.text = user.last_name
            email.text = user.email
            Picasso.get().load(user.avatar).into(imageView)
            //Picasso.get().load(user.avatar).config(Bitmap.Config.ARGB_8888).into(imageView)
        }
    }

}