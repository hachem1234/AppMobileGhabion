package com.itroad.movies.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itroad.movies.R
import com.itroad.movies.app.common.Resource
import com.itroad.movies.data.models.movies.Result
import com.itroad.movies.databinding.FragmentMoviesBinding
import com.itroad.movies.ma.app.di.injector
import com.itroad.movies.util.viewModel


class MoviesFragment : Fragment(),ListFormuleClickListener {
    private val viewModel by viewModel { injector.weatherViewModel }
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesManagmentAdapter: MoviesManagmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        binding.featureRecycler.layoutManager = LinearLayoutManager(context)

        updateAdapter()
            return binding.root

    }


    private fun updateAdapter(){
        binding.prog.visibility = View.VISIBLE
        viewModel.getMovies()
        viewModel.responseGetMovies.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                Resource.Loading -> {
                    binding.prog.visibility = View.VISIBLE

                }
                is Resource.Failure -> {
                    binding.prog.visibility = View.GONE


                }
                is Resource.Success -> {
                    binding.prog.visibility = View.GONE
                    binding.apply {
                        moviesManagmentAdapter =
                            resource.data.results?.let {
                                MoviesManagmentAdapter(context,
                                    it,this@MoviesFragment)
                            }!!
                    }!!
                    binding.featureRecycler.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = moviesManagmentAdapter

                    }



                }
            }
        }

    }

    override fun onItemClick(position: Int, data: Result) {

      Log.v("teste",data.id.toString())
        val bundle = Bundle()
        bundle.putSerializable("movies_id", data.id)
        findNavController().navigate(R.id.detailMovieFragment, bundle)

    }
}

