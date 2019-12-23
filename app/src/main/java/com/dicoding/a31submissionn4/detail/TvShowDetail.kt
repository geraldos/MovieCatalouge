package com.dicoding.a31submissionn4.detail

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.model.TvShowModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class TvShowDetail: AppCompatActivity() {
    companion object {
        const val EXTRA_TV = "extra_tv"
    }
    private var favorite: TvShowModel? = null
    private var isFavorite = false
    private var tvshow = arrayListOf<TvShowModel>()
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)
        progressBar = findViewById(R.id.progressBar)
        showLoading(true)

        tvshow = intent.getParcelableArrayListExtra<TvShowModel>(EXTRA_TV)

        progressBar.visibility = View.VISIBLE
        val handler = Handler()
        Thread(Runnable {
            try {
                Thread.sleep(4000)
            } catch (e: Exception) {

            }
            handler.post {
                tv_nama_judul.text = tvshow[0].title
                tv_description.text = tvshow[0].overview
                tv_rating.text = tvshow[0].vote_average
                tv_release.text = tvshow[0].release_date
                val imgPoster: ImageView = findViewById(R.id.tv_image)
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/${tvshow[0].poster}")
                    .apply(RequestOptions().override(350, 550))
                    .into(imgPoster)
                showLoading(false)
            }
        }).start()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}