package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.adapters.CategoryRecyclerAdapter
import com.hamidreza.newsapp.data.adapters.NewsAdapter
import com.hamidreza.newsapp.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf("sports", "action", "business", "weather", "dram", "action")
        val adapter = CategoryRecyclerAdapter(list)
        rv_category.adapter = adapter
        val linear = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_category.layoutManager = linear
        setUpNewsRecycler()
        viewModel.getBreakingNews()
        viewModel.breakingNews.observe(viewLifecycleOwner, {
            it?.let {
                newsAdapter.differ.submitList(it.articles)
            }
        })
        iv_right.setOnClickListener {
            if (linear.findLastCompletelyVisibleItemPosition() < (adapter.getItemCount() - 1)) {
                linear.smoothScrollToPosition(
                    rv_category,
                    RecyclerView.State(), adapter.itemCount
                )
            }
        }
        iv_left.setOnClickListener {
            if (linear.findLastCompletelyVisibleItemPosition() > (adapter.getItemCount() - 2)) {
                linear.smoothScrollToPosition(
                    rv_category,
                    RecyclerView.State(), 0
                )
            }
        }

    }

    fun setUpNewsRecycler() {
        rv_news.apply {
            newsAdapter = NewsAdapter()
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    fun setUpCategoryRecycler() {
        rv_category.apply {

        }
    }
}


/*
buttontest.setOnClickListener {
    AppCompatDelegate
        .setDefaultNightMode(
            AppCompatDelegate
                .MODE_NIGHT_YES);
}*/