package com.hamidreza.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamidreza.newsapp.R
import com.hamidreza.newsapp.data.model.remote.Article
import com.hamidreza.newsapp.databinding.FragmentSavedNewsBinding
import com.hamidreza.newsapp.ui.adapters.SavedNewsAdapter
import com.hamidreza.newsapp.ui.adapters.onItemClickListener
import com.hamidreza.newsapp.ui.viewmodels.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_saved_news.*

@AndroidEntryPoint
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news), onItemClickListener {

    private var _binding:FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var savedNewsAdapter: SavedNewsAdapter
    private val viewModel : ArticleViewModel by viewModels()
    private var currentList:List<Article> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedNewsBinding.bind(view)
        setUpRecyclerView()
        searchInNews()
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
            currentList = it
        }
    }

    fun searchInNews(){
        binding.edtSearch.addTextChangedListener {
            val list = searchRecyclerView(currentList)
            if (list.size >= 1){
                binding.rvSearchNews.visibility = View.VISIBLE
                savedNewsAdapter.differ.submitList(list)
                binding.tvEmpty.visibility = View.INVISIBLE
            }else{
                binding.rvSearchNews.visibility = View.INVISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }

    fun searchRecyclerView(items:List<Article>): MutableList<Article> {
        val query = binding.edtSearch.text.toString().trim()
        val searchList = mutableListOf<Article>()
        if (query.isNotEmpty()){
            for ( i in items){
                if (i.title.contains(query)){
                    searchList.add(i)
                }
            }
        }else{
            return items as MutableList<Article>
        }
        return searchList
    }

    override fun onClick(item: Article) {
        val action = SavedNewsFragmentDirections.actionSavedFragmentToArticleFragment(item)
        findNavController().navigate(action)
    }

}