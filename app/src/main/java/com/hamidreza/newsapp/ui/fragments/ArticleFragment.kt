package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.databinding.FragmentArticleBinding
import com.hamidreza.newsapp.ui.viewmodels.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val viewModel: ArticleViewModel by viewModels()
    private var _binding:FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private val args:ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)
        binding.apply {
            val article = args.article
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
            fab.setOnClickListener {
                viewModel.insertArticle(article)
            }
        }
        viewModel.getArticles.observe(viewLifecycleOwner){
            for (i in it){
                Log.i("Room", "Room List: ${i.source} ")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}