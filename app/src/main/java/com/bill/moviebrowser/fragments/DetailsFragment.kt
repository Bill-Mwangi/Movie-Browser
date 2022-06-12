package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.bill.moviebrowser.MovieAdapter
import com.bill.moviebrowser.room.Movie
import com.bill.moviebrowser.viewmodel.MovieViewModel
import com.example.moviebrowser.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(), MovieAdapter.OnItemClickListener {
  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private val movieAdapter: MovieAdapter by lazy { MovieAdapter(this) }
  private val viewModel: MovieViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
    viewModel.getRecommendations(args.movie.tvdbID, 1)

    binding.apply {
      if (args.movie.backdrop != null) imageview.load("https://image.tmdb.org/t/p/w780${args.movie.backdrop}")
      else imageview.load("https://image.tmdb.org/t/p/w780${args.movie.poster}")

      titleTv.text = args.movie.title
      descTv.text = args.movie.description


      viewModel.recommendations.observe(viewLifecycleOwner) { movie ->
        movie?.let {
          movieAdapter.changeList(it)
        }
      }



      recommendationsRv.apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = movieAdapter
      }
    }

    return binding.root
  }

  override fun onItemClick(position: Int, view: View?) {
    val clickedItem: Movie = movieAdapter.movieList[position]
    Toast.makeText(context, "$clickedItem.title clicked", Toast.LENGTH_SHORT).show()
  }
}