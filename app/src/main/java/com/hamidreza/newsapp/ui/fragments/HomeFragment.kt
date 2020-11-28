package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.adapters.CategoryRecyclerAdapter
import com.hamidreza.newsapp.data.adapters.NewsAdapter
import com.hamidreza.newsapp.data.model.local.Category
import com.hamidreza.newsapp.ui.viewmodels.NewsViewModel
import com.hamidreza.newsapp.utils.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter
    lateinit var categoryAdapter: CategoryRecyclerAdapter
    lateinit var categoryList:List<Category>
    lateinit var linear:LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)/*
        val list = listOf("عمومی", "سلامت", "علمی", "ورزشی", "تکنولوژی", "سرگرمی", "تجارت")
        val adapter = CategoryRecyclerAdapter(list)
        rv_category.adapter = adapter
        val linear = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_category.layoutManager = linear*/
        linear = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        setUpCategoryRecycler()
        setUpNewsRecycler()
        viewModel.getBreakingNews("us", 1, "general")
        viewModel.breakingNews.observe(viewLifecycleOwner, { response ->

            when (response) {
                is ResultWrapper.Loading -> showProgressBar()
                is ResultWrapper.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    };
                }
                is ResultWrapper.Error -> {
                    hideProgressBar()
                    when (response.msg) {
                        "connectivity" -> Toast.makeText(
                            requireContext(),
                            "اینترنت خود را چک کنید",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        })
        iv_right.setOnClickListener {
            if (linear.findLastCompletelyVisibleItemPosition() < (categoryAdapter.getItemCount() - 1)) {
                linear.smoothScrollToPosition(
                    rv_category,
                    RecyclerView.State(), categoryAdapter.itemCount
                )
            }
        }
        iv_left.setOnClickListener {
            if (linear.findLastCompletelyVisibleItemPosition() > (categoryAdapter.getItemCount() - 2)) {
                linear.smoothScrollToPosition(
                    rv_category,
                    RecyclerView.State(), 0
                )
            }
        }

    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
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
            //"سلامت", "علمی", "ورزشی", "تکنولوژی", "سرگرمی", "تجارت"
            categoryList = listOf(Category("عمومی","general"),
            Category("سرگرمی","entertainment"),
            Category("تکنولوژی","technology"),
            Category("ورزشی","sports"),
            Category("علمی","science"),
            Category("سلامت","health"),
            Category("تجارت","business")
            )
            categoryAdapter = CategoryRecyclerAdapter(categoryList)
            adapter = categoryAdapter
            layoutManager = linear
        }
        categoryAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            newsAdapter.differ.submitList(mutableListOf())
            viewModel.getBreakingNews("us", 1, "$it")
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