package com.example.imdbmoviapp.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imdbmoviapp.BR

import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.adapter.MovieListAdapter
import com.example.imdbmoviapp.base.AppInjectBindFragment
import com.example.imdbmoviapp.databinding.FragmentMovieListBinding
import com.example.imdbmoviapp.enums.ManageStatusEnum
import com.example.imdbmoviapp.model.Search
import com.example.imdbmoviapp.util.AppConstants
import com.example.imdbmoviapp.util.ProgressDialogUtil
import com.example.imdbmoviapp.util.ToastHelper
import com.example.imdbmoviapp.viewModel.MovieListViewModel
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : AppInjectBindFragment(),MovieListAdapter.OnItemClickListener {

    @Inject
    lateinit var viewModel:MovieListViewModel

    lateinit var binding: FragmentMovieListBinding

    private fun initRecyclerView() {

        binding.rvMovieList.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = MovieListAdapter(mutableListOf(),this@MovieListFragment)
            setLoadingMoreEnabled(true)
            setPullRefreshEnabled(false)
            setRefreshProgressStyle(ProgressStyle.BallBeat)
            setLoadingMoreProgressStyle(ProgressStyle.BallRotate)
            setLimitNumberToCallLoadMore(1)
        }

        binding.rvMovieList.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
            }

            override fun onLoadMore() {
                loadData(withIncrement = true)
            }
        })

    }

    private fun loadData(withIncrement:Boolean=false){
        viewModel.loadMovies(withIncrement).observe(viewLifecycleOwner, Observer {

            when (it.status) {
                ManageStatusEnum.LOADING->{
                    ProgressDialogUtil.showProgressDialog(requireContext(), false)
                }

                ManageStatusEnum.LOADING_DISMISS->{
                   ProgressDialogUtil.dismissProgressDialog()
                    binding.rvMovieList.loadMoreComplete()
                }

                ManageStatusEnum.NO_DATA_FOUND->{
                    ToastHelper.showToast(requireContext(), getString(R.string.no_data_found))
                }

                ManageStatusEnum.TOO_MANY_RESULTS-> {
                    (binding.rvMovieList.adapter as MovieListAdapter).clearAllItems()
                    ToastHelper.showToast(requireContext(), getString(R.string.too_many_results))
                }

                ManageStatusEnum.FAILED-> {
                    ToastHelper.showToast(requireContext(), getString(R.string.loading_failed))
                }

                ManageStatusEnum.SUCCESS-> {
                    (binding.rvMovieList.adapter as MovieListAdapter).addItems(
                        it.dataList ?: mutableListOf()
                    )
                }

            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity?.finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binding.setVariable(BR.viewModel,viewModel)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        loadData(withIncrement = false)

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.offSet=1
                loadData(withIncrement = false)
            }
        })

    }

    override fun onItemClicked(position: Int, item: Search) {

        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(AppConstants.MOVIE_ID,item.imdbID)
        startActivity(intent)
    }

}
