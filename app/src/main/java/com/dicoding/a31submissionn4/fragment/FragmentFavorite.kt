package com.dicoding.a31submissionn4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dicoding.a31submissionn4.R
import com.dicoding.a31submissionn4.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class FragmentFavorite : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.view_pager)

        val adapter = SectionsPagerAdapter(childFragmentManager)
        adapter.addFragment(MovieFavoriteFragment(), resources.getString(R.string.title_movie))
        adapter.addFragment(TvShowFavoriteFragment(), resources.getString(R.string.title_tvShow))

        viewPager!!.setAdapter(adapter)
        tabLayout!!.setupWithViewPager(viewPager)
    }

}