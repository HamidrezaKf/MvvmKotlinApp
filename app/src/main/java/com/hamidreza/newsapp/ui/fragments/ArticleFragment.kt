package com.hamidreza.newsapp.ui.fragments

import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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
                webViewClient = object : WebViewClient(){
                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        webView.loadUrl("about:blank")
                        val action = ArticleFragmentDirections.actionGlobalShowAlertDialogFragment()
                        Navigation.findNavController(requireView()).navigate(action)
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.progressBar2.visibility = View.INVISIBLE
                    }
                }
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
    }
}