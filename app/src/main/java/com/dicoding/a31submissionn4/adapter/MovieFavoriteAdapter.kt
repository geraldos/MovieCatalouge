package com.dicoding.a31submissionn4.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.detail.MovieDetail
import com.dicoding.a31submissionn4.model.FavoriteModel
import com.dicoding.a31submissionn4.model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieFavoriteAdapter() : RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder>() {
    private val mData = ArrayList<FavoriteModel>()

    fun setFavorites(movieFav: java.util.ArrayList<FavoriteModel>) {
        mData.clear()
        mData.addAll(movieFav)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = this.mData.size

    inner class MovieFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(moviefavorite: FavoriteModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w342/${moviefavorite.poster}")
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)
                tv_item_name.text = moviefavorite.title
                tv_item_release.text = moviefavorite.release_date
                tv_item_rating.text = moviefavorite.vote_average
                tv_item_description.text = moviefavorite.overview

                itemView.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Kamu memilih ${moviefavorite.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(itemView.context, MovieDetail::class.java)
                    val movies = ArrayList<MovieModel>()
                    val loMovie = MovieModel(
                        title = moviefavorite.title,
                        overview = moviefavorite.overview,
                        vote_average = moviefavorite.vote_average,
                        release_date = moviefavorite.release_date,
                        poster = moviefavorite.poster
                    )
                    movies.add(loMovie)
                    intent.putExtra(MovieDetail.EXTRA_MOVIE,movies)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}