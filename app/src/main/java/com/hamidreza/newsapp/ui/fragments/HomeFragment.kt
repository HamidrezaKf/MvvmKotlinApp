package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.ui.adapters.CategoryRecyclerAdapter
import com.hamidreza.newsapp.ui.adapters.NewsAdapter
import com.hamidreza.newsapp.data.model.local.Category
import com.hamidreza.newsapp.databinding.FragmentHomeBinding
import com.hamidreza.newsapp.ui.adapters.NewsLoadStateAdapter
import com.hamidreza.newsapp.ui.adapters.NewsPagingAdapter
import com.hamidreza.newsapp.ui.viewmodels.NewsViewModel
import com.hamidreza.newsapp.utils.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


    private var _binding : FragmentHomeBinding ? = null
    val binding get() = _binding!!
    private val TAG = "HomeFragment"
    val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter
    lateinit var newsPagingAdapter: NewsPagingAdapter
    lateinit var categoryAdapter: CategoryRecyclerAdapter
    lateinit var categoryList:List<Category>
    lateinit var linear:LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)/*
        val list = listOf("عمومی", "سلامت", "علمی", "ورزشی", "تکنولوژی", "سرگرمی", "تجارت")
        val adapter = CategoryRecyclerAdapter(list)
        rv_category.adapter = adapter
        val linear = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rv_category.layoutManager = linear*/
        _binding = FragmentHomeBinding.bind(view)
        linear = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        setUpCategoryRecycler()
        setUpNewsRecycler()
        /*
        if (viewModel.breakingNews.value == null){
            viewModel.getBreakingNews("us", 1, "general")
        }
        viewModel.breakingNews.observe(viewLifecycleOwner, { response ->
            Log.i(TAG, "onViewCreated: observe")
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

        })*/
        viewModel.news.observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        }
        binding.ivRight.setOnClickListener {
            if (linear.findLastCompletelyVisibleItemPosition() < (categoryAdapter.getItemCount() - 1)) {
                linear.smoothScrollToPosition(
                    binding.rvCategory,
                    RecyclerView.State(), categoryAdapter.itemCount
                )
            }
        }
        binding.ivLeft.setOnClickListener {
            if (linear.findLastCompletelyVisibleItemPosition() > (categoryAdapter.getItemCount() - 2)) {
                linear.smoothScrollToPosition(
                    binding.rvCategory,
                    RecyclerView.State(), 0
                )
            }
        }

    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun setUpNewsRecycler() {
        binding.rvNews.apply {
            //newsAdapter = NewsAdapter()
            newsPagingAdapter = NewsPagingAdapter()
            adapter = newsPagingAdapter.withLoadStateFooter(footer = NewsLoadStateAdapter{
                newsPagingAdapter.retry()
            })
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    fun setUpCategoryRecycler() {
        binding.rvCategory.apply {
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
            viewModel.row_index_view_model.observe(viewLifecycleOwner, Observer {
                categoryAdapter.row_index = it
            })
            adapter = categoryAdapter
            layoutManager = linear
        }
        categoryAdapter.setOnItemClickListener { title,position ->
            Toast.makeText(requireContext(), "$title", Toast.LENGTH_SHORT).show()
           // newsAdapter.differ.submitList(mutableListOf())
           // viewModel.getBreakingNews("us", 1, "$title")
            binding.rvNews.scrollToPosition(0)
            viewModel.setCategory("$title")
            viewModel.row_index_view_model.value = position
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


/*
buttontest.setOnClickListener {
    AppCompatDelegate
        .setDefaultNightMode(
            AppCompatDelegate
                .MODE_NIGHT_YES);
}*/