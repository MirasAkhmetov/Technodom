package com.project.technodom.main.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.project.technodom.R
import com.project.technodom.entity.Movie
import com.project.technodom.global.di.ServiceProperties
import kotlinx.android.synthetic.main.item_movie_list_horizontal.view.*

class FirstFragmentAdapterTwo(val onBottomReachedListener: () -> Unit) :
    RecyclerView.Adapter<FirstFragmentAdapterTwo.MyViewHolder>() {

    private var dataList: List<Movie> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie_list_horizontal,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == dataList.size - 10) onBottomReachedListener.invoke()
        holder.bind(dataList[position])
    }

    fun submitData(dataList: List<Movie>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.apply {

                val moviePosterURL = ServiceProperties.POSTER_BASE_URL + movie?.posterPath
                Glide.with(context)
                    .load(moviePosterURL)
                    .optionalCircleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(cv_iv_movie_poster_horizontal)
            }
        }
    }
}