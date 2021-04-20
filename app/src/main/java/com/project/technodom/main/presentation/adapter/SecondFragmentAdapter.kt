package com.project.technodom.main.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.technodom.R
import com.project.technodom.entity.Movie
import com.project.technodom.global.di.ServiceProperties.POSTER_BASE_URL
import kotlinx.android.synthetic.main.item_movie_list_second.view.*

class SecondFragmentAdapter( val onBottomReachedListener: () -> Unit): RecyclerView.Adapter<SecondFragmentAdapter.MyViewHolder>() {

    private var dataList: List<Movie> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie_list_second,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == dataList.size - 10) onBottomReachedListener.invoke()
        holder.bind(dataList[position])
    }

    fun submitData(dataList : List<Movie>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie){
            itemView.apply {
                cv_movie_title_second.text = movie?.title
                cv_movie_release_date_second.text =  movie?.releaseDate

                val moviePosterURL = POSTER_BASE_URL + movie?.posterPath
                Glide.with(context)
                    .load(moviePosterURL)
                    .into(cv_iv_movie_poster_second)
            }
        }
    }
}