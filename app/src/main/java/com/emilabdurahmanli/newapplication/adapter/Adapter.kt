package com.emilabdurahmanli.newapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emilabdurahmanli.newapplication.R
import com.emilabdurahmanli.newapplication.model.News
import com.squareup.picasso.Picasso

class Adapter (list : List<News>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var list : List<News>
    init {
        this.list=list
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.titleText)
        var description = itemView.findViewById<TextView>(R.id.descriptionText)
        var image = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(list.get(position).title)
        holder.description.setText(list.get(position).description)
        Picasso.get().load(list.get(position).image_url).resize(800, 800).centerCrop().into(holder.image)
    }
}