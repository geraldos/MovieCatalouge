package com.dicoding.a31submissionn4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.adapter.TvShowAdapter
import com.dicoding.a31submissionn4.viewModel.TvShowViewModel

class TvShowFragment: Fragment() {
    private lateinit var adapter: TvShowAdapter
    private lateinit var mainViewModel: TvShowViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tvshow, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShow = view.findViewById<View>(R.id.rv_tv) as RecyclerView

        adapter = TvShowAdapter()
        tvShow.layoutManager = LinearLayoutManager(context)
        tvShow.adapter = adapter
        progressBar = view.findViewById(R.id.progressBar)
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel::class.java)
        mainViewModel.setTv()
        mainViewModel.getTv().observe(viewLifecycleOwner, Observer { tvShowItems ->
            if (tvShowItems != null) {
                adapter.setData(tvShowItems)
                showLoading(false)
            }
        })
        mainViewModel.setTv()
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}