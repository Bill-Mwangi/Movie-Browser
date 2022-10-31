package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.bill.moviebrowser.CastAdapter
import com.bill.moviebrowser.RecommendationAdapter
import com.bill.moviebrowser.viewmodel.MovieViewModel
import com.example.moviebrowser.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(), RecommendationAdapter.OnItemClickListener,
  CastAdapter.OnItemClickListener {
  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()
  private val castAdapter: CastAdapter by lazy { CastAdapter(this) }
  private val recommendationsAdapter: RecommendationAdapter by lazy { RecommendationAdapter(this) }
  private val viewModel: MovieViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
    viewModel.flush()
    viewModel.data(args.movie.movieId)

    binding.apply {
      imageview.load("https://image.tmdb.org/t/p/w780${args.movie.backdrop}")

      titleTv.text = args.movie.title
      descTv.text = args.movie.description

      recommendationsRv.apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = recommendationsAdapter
      }

      castRv.apply {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = castAdapter
      }

      viewModel.recommendations.observe(viewLifecycleOwner) { movie ->
        movie?.let {
          recommendationsAdapter.changeList(it)
        }
      }

      viewModel.castList.observe(viewLifecycleOwner) { cast ->
        cast?.let {
          castAdapter.changeList(it)
        }
      }
    }

    return binding.root
  }

  override fun onItemClick(position: Int, view: View?) {
    view?.findNavController()
      ?.navigate(DetailsFragmentDirections.navigateToSelf(recommendationsAdapter.movieList[position]))
  }

  override fun onCastItemClick(position: Int, view: View?) {
  }
}