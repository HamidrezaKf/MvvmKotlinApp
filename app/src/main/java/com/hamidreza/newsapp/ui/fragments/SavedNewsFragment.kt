package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.model.remote.Article
import com.hamidreza.newsapp.databinding.FragmentSavedNewsBinding
import com.hamidreza.newsapp.ui.adapters.SavedNewsAdapter
import com.hamidreza.newsapp.ui.adapters.onItemClickListener
import com.hamidreza.newsapp.ui.viewmodels.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news), onItemClickListener {

    private var _binding:FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var savedNewsAdapter: SavedNewsAdapter
    private val viewModel : ArticleViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedNewsBinding.bind(view)
        setUpRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setUpRecyclerView(){
        savedNewsAdapter = SavedNewsAdapter(this@SavedNewsFragment)
        binding.rvSearchNews.apply {
            adapter = savedNewsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getArticles.observe(viewLifecycleOwner){
            savedNewsAdapter.differ.submitList(it)
        }
    }

    override fun onClick(item: Article) {

    }

}