package com.hamidreza.newsapp.ui.fragments.introslides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.databinding.FragmentIntroSliderBinding
import com.hamidreza.newsapp.ui.adapters.IntroViewPagerAdapter

class IntroSliderFragment : Fragment(R.layout.fragment_intro_slider) {

    private var _binding:FragmentIntroSliderBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIntroSliderBinding.bind(view)
        val list = listOf(
            FirstFragment(), SecondFragment(), ThirdFragment()
        )

        val adapter = IntroViewPagerAdapter(list,requireActivity().supportFragmentManager,lifecycle)
        binding.apply {
            viewPager2.adapter = adapter
            TabLayoutMediator(tabLayout,viewPager2){
                    tab, position ->
            }.attach()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}