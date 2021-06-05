package com.tanay.flickrdemo.ui.launch.search

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tanay.flickrdemo.R

class PictureAdapter(private val listener: OnLoadNextPageListener) :
    RecyclerView.Adapter<PictureAdapter.PictureHolder>() {

    private val list = ArrayList<String>()


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        return PictureHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_row, null))
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        holder.imageView.layoutParams.height = Resources.getSystem().displayMetrics.widthPixels / 2
        Glide.with(holder.imageView.context).load(list[position])
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)
        if (list.size - 1 == position) {
            listener.loadNextPage()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PictureHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.image_view)
    }

    fun update(data: ArrayList<String>?) {
        if (data == null) {
            list.clear()
            notifyDataSetChanged()
            return
        }
        list.addAll(data)
        notifyDataSetChanged()
    }

    interface OnLoadNextPageListener {
        fun loadNextPage()
    }
}