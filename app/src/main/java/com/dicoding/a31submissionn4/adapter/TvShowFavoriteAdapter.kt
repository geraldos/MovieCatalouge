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
import com.dicoding.a31submissionn4.detail.TvShowDetail
import com.dicoding.a31submissionn4.fragment.TvShowFavoriteFragment
import com.dicoding.a31submissionn4.model.FavoriteModel
import com.dicoding.a31submissionn4.model.MovieModel
import com.dicoding.a31submissionn4.model.TvShowModel
import kotlinx.android.synthetic.main.item_movie.view.*

class TvShowFavoriteAdapter(tvshowFavoriteFragment: TvShowFavoriteFragment) : RecyclerView.Adapter<TvShowFavoriteAdapter.TvShowFavoriteViewHolder>() {

    var listTvShowFavorite = ArrayList<FavoriteModel>()
        set(listTvShowFavorite) {
            if (listTvShowFavorite.size > 0) {
                this.listTvShowFavorite.clear()
            }
            this.listTvShowFavorite.addAll(listTvShowFavorite)
            notifyDataSetChanged()
        }

    fun addItem(favoritemovie: FavoriteModel) {
        this.listTvShowFavorite.add(favoritemovie)
        notifyItemInserted(this.listTvShowFavorite.size - 1)
    }

    fun updateItem(position: Int, favoritemovie: FavoriteModel) {
        this.listTvShowFavorite[position] = FavoriteModel()
        notifyItemChanged(position, favoritemovie)
    }

    fun removeItem(position: Int) {
        this.listTvShowFavorite.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listTvShowFavorite.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        return TvShowFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowFavoriteViewHolder, position: Int) {
        holder.bind(listTvShowFavorite[position])
    }

    override fun getItemCount(): Int = this.listTvShowFavorite.size

    inner class TvShowFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshowfavorite: FavoriteModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w342/${tvshowfavorite.poster}")
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)
                tv_item_name.text = tvshowfavorite.title
                tv_item_release.text = tvshowfavorite.release_date
                tv_item_rating.text = tvshowfavorite.vote_average
                tv_item_description.text = tvshowfavorite.overview

                itemView.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Kamu memilih ${tvshowfavorite.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(itemView.context, TvShowDetail::class.java)
                    val movies = ArrayList<TvShowModel>()
                    val loMovie = TvShowModel(
                        title = tvshowfavorite.title,
                        overview = tvshowfavorite.overview,
                        vote_average = tvshowfavorite.vote_average,
                        release_date = tvshowfavorite.release_date,
                        poster = tvshowfavorite.poster
                    )
                    movies.add(loMovie)
                    intent.putExtra(TvShowDetail.EXTRA_TV,movies)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}