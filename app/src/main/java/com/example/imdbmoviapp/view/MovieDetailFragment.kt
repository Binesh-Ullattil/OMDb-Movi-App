package com.example.imdbmoviapp.view

import com.example.imdbmoviapp.util.NetworkStatusHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.imdbmoviapp.BR

import com.example.imdbmoviapp.R
import com.example.imdbmoviapp.base.AppInjectBindFragment
import com.example.imdbmoviapp.databinding.FragmentMovieDetailBinding
import com.example.imdbmoviapp.enums.ManageStatusEnum
import com.example.imdbmoviapp.util.AppConstants
import com.example.imdbmoviapp.util.ProgressDialogUtil
import com.example.imdbmoviapp.util.ToastHelper
import com.example.imdbmoviapp.viewModel.MovieDetailViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : AppInjectBindFragment() {

    @Inject
    lateinit var viewModel:MovieDetailViewModel

    lateinit var binding: FragmentMovieDetailBinding

    private var movieId:String?=""

    private fun loadData(movieId:String){
        viewModel.loadMovieDetails(movieId).observe(viewLifecycleOwner, Observer {

            when (it.status) {
                ManageStatusEnum.LOADING -> {
                    //binding.progressBar.visibility=View.VISIBLE
                    ProgressDialogUtil.showProgressDialog(requireContext(), false)
                }
                ManageStatusEnum.LOADING_DISMISS -> {
                    //binding.progressBar.visibility=View.GONE
                    ProgressDialogUtil.dismissProgressDialog()
                }
                ManageStatusEnum.INTERNAL_SERVER_ERROR -> {
                    ToastHelper.showToast(requireContext(),  getString(R.string.internal_server_error))
                }
                ManageStatusEnum.NO_DATA_FOUND -> {
                    ToastHelper.showToast(requireContext(),  getString(R.string.no_data_found))
                }

                ManageStatusEnum.FAILED-> {
                    ToastHelper.showToast(requireContext(), getString(R.string.loading_failed))
                }

                ManageStatusEnum.SUCCESS-> {
                    binding.setVariable(BR.item,it.data)
                    binding.executePendingBindings()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movieId = arguments?.getString(AppConstants.MOVIE_ID)!!
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            //findNavController().navigate(R.id.action_pop_to_movies)
            activity?.finish()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_movie_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            //findNavController().navigate(R.id.action_pop_to_movies)
            activity?.finish()
        }

        if(NetworkStatusHelper.isNetworkAvailable(requireContext())){
            loadData(movieId!!)
        }else{
            ToastHelper.showToast(requireContext(), getString(R.string.no_internet_connection))
        }
    }

}
