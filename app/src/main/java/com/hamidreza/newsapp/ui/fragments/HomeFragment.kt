package com.hamidreza.newsapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.model.Category
import com.hamidreza.newsapp.data.model.Article
import com.hamidreza.newsapp.databinding.FragmentHomeBinding
import com.hamidreza.newsapp.ui.adapters.*
import com.hamidreza.newsapp.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), onItemClickListener {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    //private var isNightMode: Boolean = false
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!
    private val TAG = "HomeFragment"
    val viewModel: NewsViewModel by viewModels()
    lateinit var newsPagingAdapter: NewsPagingAdapter
    lateinit var categoryAdapter: CategoryRecyclerAdapter
    lateinit var categoryList: List<Category>
    lateinit var linear: LinearLayoutManager
    private var rowPosition = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        linear = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        setUpCategoryRecycler()
        setUpNewsRecycler()


        viewModel.news.observe(viewLifecycleOwner) {
            newsPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.searchNews.observe(viewLifecycleOwner) {
            if (binding.edtSearch.text.length != 0) {
                newsPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }


        binding.edtSearch.addTextChangedListener {
            it?.let {
                if (it.toString().trim().isNotEmpty()) {
                    viewModel.setSearch(it.toString())
                }
            }
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


    fun setUpNewsRecycler() {
        binding.rvNews.apply {
            //newsAdapter = NewsAdapter()
            newsPagingAdapter = NewsPagingAdapter(this@HomeFragment)
            adapter = newsPagingAdapter.withLoadStateFooter(footer = NewsLoadStateAdapter {
                newsPagingAdapter.retry()
            })
            layoutManager = LinearLayoutManager(requireContext())
        }
        newsPagingAdapter.addLoadStateListener { loadState ->
            binding.apply {
                btnRetry.setOnClickListener {
                    newsPagingAdapter.retry()
                }
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvNews.isVisible = loadState.source.refresh is LoadState.NotLoading
                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                tvStatus.isVisible = loadState.source.refresh is LoadState.Error
                if (loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && newsPagingAdapter.itemCount < 1
                ) {
                    rvNews.isVisible = false
                    tvEmpty.isVisible = true
                } else {
                    tvEmpty.isVisible = false
                }
            }
        }
    }

    fun setUpCategoryRecycler() {
        binding.rvCategory.apply {
            categoryList = listOf(
                Category("عمومی", "general"),
                Category("سرگرمی", "entertainment"),
                Category("تکنولوژی", "technology"),
                Category("ورزشی", "sports"),
                Category("علمی", "science"),
                Category("سلامت", "health"),
                Category("تجارت", "business")
            )
            categoryAdapter = CategoryRecyclerAdapter(categoryList)
            categoryAdapter.row_index = rowPosition
            adapter = categoryAdapter
            layoutManager = linear
        }
        categoryAdapter.setOnItemClickListener { title, position ->
            binding.rvNews.scrollToPosition(0)
            viewModel.setCategory("$title")
            binding.edtSearch.setText("")
            rowPosition = position
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(item: Article) {
        val action = HomeFragmentDirections.actionHomeFragmentToArticleFragment(item)
        findNavController().navigate(action)
    }


}

