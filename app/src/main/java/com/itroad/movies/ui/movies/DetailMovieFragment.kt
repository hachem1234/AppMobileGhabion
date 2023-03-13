package com.itroad.movies.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.itroad.movies.R
import com.itroad.movies.app.common.Constants
import com.itroad.movies.app.common.Resource
import com.itroad.movies.databinding.FragmentDetailMovieBinding
import com.itroad.movies.databinding.FragmentMoviesBinding
import com.itroad.movies.ma.app.di.injector
import com.itroad.movies.util.viewModel


class DetailMovieFragment : Fragment() {
    private var id_movie:Int=0
    private val viewModel by viewModel { injector.weatherViewModel }
    private lateinit var binding: FragmentDetailMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_movie, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUi()
    }


    private fun updateUi(){
        binding.prog.visibility = View.VISIBLE
        if (arguments?.getSerializable("movies_id") != null){
            id_movie= arguments?.getSerializable("movies_id") as Int
        }
        viewModel.getMoviesDetail(id_movie)
        viewModel.responseGetMoviesDetail.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                Resource.Loading -> {
                    binding.prog.visibility = View.VISIBLE

                }
                is Resource.Failure -> {
                    binding.prog.visibility = View.GONE


                }
                is Resource.Success -> {
                    Log.v("teshhtes","teste")

                    binding.prog.visibility = View.GONE
                    binding.titleText.text=resource.data.original_title?:""
                    binding.title2Text.text=resource.data.release_date?:""
                    binding.textView.text=resource.data.overview?:""

                    context?.let {
                        Glide.with(it)
                            .load(Constants.BASE_URL_IMAGE+resource.data.poster_path?:"")
                            .into(binding.imageView)
                    }


                }
            }
        }

    }

}