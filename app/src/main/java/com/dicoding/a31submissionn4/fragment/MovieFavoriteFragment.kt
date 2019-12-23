package com.dicoding.a31submissionn4.fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.adapter.MovieFavoriteAdapter
import com.dicoding.a31submissionn4.db.AppDatabase
import com.dicoding.a31submissionn4.model.FavoriteModel
import java.util.*

class MovieFavoriteFragment : Fragment() {

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }
    var adapter: MovieFavoriteAdapter? = null
    var movieFav: ArrayList<FavoriteModel>? = null
    var recyclerView: RecyclerView? = null
    private var db: AppDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewMovie = inflater.inflate(R.layout.fragment_movie_favorite, container, false)
        return viewMovie
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Room.databaseBuilder(context!!, AppDatabase::class.java, "favoritedb").build()
        adapter = MovieFavoriteAdapter()
        recyclerView = view.findViewById(R.id.rv_movie_favorite)
        getMovieFav()
    }

    @SuppressLint("StaticFieldLeak")
    private fun getMovieFav() {
        object : AsyncTask<Void?, Void?, ArrayList<FavoriteModel>>() {
            override fun doInBackground(vararg params: Void?): ArrayList<FavoriteModel> {
                movieFav = db!!.favoriteDao()!!.selectMovieFavorite() as ArrayList<FavoriteModel>?
                return movieFav as ArrayList<FavoriteModel>
            }
            override fun onPostExecute(result: ArrayList<FavoriteModel>) {
                adapter!!.setFavorites(result)
                adapter!!.notifyDataSetChanged()
                Log.d("movieData", movieFav.toString())
                recyclerView!!.adapter = adapter
            }
        }.execute()
    }
}