package com.dicoding.a31submissionn4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.adapter.TvShowFavoriteAdapter
import kotlinx.android.synthetic.main.fragment_tvshow_favorite.*

class TvShowFavoriteFragment : Fragment() {

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    private lateinit var adapter: TvShowFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewMovie = inflater.inflate(R.layout.fragment_tvshow_favorite, container, false)
        return viewMovie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_tvshow_favorite.layoutManager = LinearLayoutManager(context)
        rv_tvshow_favorite.setHasFixedSize(true)
        adapter = TvShowFavoriteAdapter(this)
        rv_tvshow_favorite.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}