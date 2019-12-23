package com.dicoding.a31submissionn4.detail

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.db.AppDatabase
import com.dicoding.a31submissionn4.model.FavoriteModel
import com.dicoding.a31submissionn4.model.MovieModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_ID = "extra_id"
    }

    private var favorite: FavoriteModel? = null
    private lateinit var progressBar: ProgressBar
    private var isFavorite = false
    private var isMovie = false
    private var movie = arrayListOf<MovieModel>()
    private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        progressBar = findViewById(R.id.progressBar)
        movie = intent.getParcelableArrayListExtra<MovieModel>(EXTRA_MOVIE)
        isMovie = intent.getBooleanExtra(EXTRA_TYPE, true)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        checkIsFav(id)
        showLoading(true)

        val handler = Handler()
        Thread(Runnable {
            try {
                Thread.sleep(4000)
            } catch (e: Exception) {
            }
            handler.post {
                tv_nama_judul.text = movie[0].title
                tv_description.text = movie[0].overview
                tv_rating.text = movie[0].vote_average
                tv_release.text = movie[0].release_date
                val imgPoster: ImageView = findViewById(R.id.tv_image)
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/${movie[0].poster}")
                    .apply(RequestOptions().override(350, 550))
                    .into(imgPoster)
                db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "favoritedb").build()
                showLoading(false)
            }
        }).start()
    }

    @SuppressLint("StaticFieldLeak")
    private fun insertData(favorite: FavoriteModel?) {
        object : AsyncTask<Void?, Void?, Long>() {
            override fun doInBackground(vararg params: Void?): Long {
                val status = db!!.favoriteDao()!!.insertFavorite(favorite)
                Log.d("status_db", status.toString())
                return status
            }
            override fun onPostExecute(aLong: Long) {
                isFavorite = true
                Toast.makeText(applicationContext, "Berhasil Menambahkan Favorite", Toast.LENGTH_SHORT).show()
            }
        }.execute()
    }

    @SuppressLint("StaticFieldLeak")
    private fun checkIsFav(id: Int) {
        object : AsyncTask<Void?, Void?, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                val checkIsFav = db!!.favoriteDao()!!.checkIsFavorit(id)
                isFavorite = checkIsFav != null
                return isFavorite
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun deleteFav(id: Int) {
        object : AsyncTask<Void?, Void?, Int>() {
            override fun doInBackground(vararg params: Void?): Int {
                return db!!.favoriteDao()!!.deleteFavorite(id)
            }
            override fun onPostExecute(aInteger: Int) {
                isFavorite = false
                Toast.makeText(applicationContext, "Berhasil Menghapus Favorite", Toast.LENGTH_SHORT).show()
            }
        }.execute()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        if (isFavorite) {
            menu.getItem(0).setIcon(R.drawable.ic_favorite_red_24dp)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            if (isFavorite) {
                deleteFav(favorite!!.id)
                item.setIcon(R.drawable.ic_favorite_white_24dp)
            } else {
                insertData(favorite)
                item.setIcon(R.drawable.ic_favorite_red_24dp)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
