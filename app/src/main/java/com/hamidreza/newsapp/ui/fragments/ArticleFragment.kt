package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding:FragmentArticleBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}