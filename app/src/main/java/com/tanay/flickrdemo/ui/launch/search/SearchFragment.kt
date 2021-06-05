package com.tanay.flickrdemo.ui.launch.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.tanay.flickrdemo.BR
import com.tanay.flickrdemo.R
import com.tanay.flickrdemo.databinding.FragmentSearchBinding
import com.tanay.flickrdemo.ui.base.BaseFragment
import com.tanay.flickrdemo.utils.hideKeyboard
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(), SearchNavigator,
    PictureAdapter.OnLoadNextPageListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var searchViewModel: SearchViewModel
    private val adapter = PictureAdapter(this)

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun getViewModel(): SearchViewModel {
        searchViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        return searchViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel.setNavigator(this)

        observerDataChange()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        getViewDataBinding().recyclerView.layoutManager = GridLayoutManager(context, 2)
        getViewDataBinding().recyclerView.adapter = adapter
    }

    private fun observerDataChange() {
        searchViewModel.getPicLiveData().observe(this, adapter::update)
    }

    override fun activity(): Activity {
        return requireActivity()
    }

    override fun context(): Context {
        return requireContext()
    }

    override fun hideKeyboard() {
        getViewDataBinding().etSearch.hideKeyboard()
    }

    override fun loadNextPage() {
        searchViewModel.nextPage()
    }


}