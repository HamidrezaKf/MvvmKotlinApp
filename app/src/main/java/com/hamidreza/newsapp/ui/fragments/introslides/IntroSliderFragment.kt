package com.hamidreza.newsapp.ui.fragments.introslides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.ui.adapters.IntroViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_intro_slider.*

class IntroSliderFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(
            FirstFragment(), SecondFragment(), ThirdFragment()
        )

        val adapter = IntroViewPagerAdapter(list,requireActivity().supportFragmentManager,lifecycle)
        view_pager2.adapter = adapter

        TabLayoutMediator(tab_layout,view_pager2){
                tab, position ->
        }.attach()

    }

}