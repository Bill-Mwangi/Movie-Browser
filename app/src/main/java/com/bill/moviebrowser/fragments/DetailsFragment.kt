package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.moviebrowser.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
  private lateinit var binding: FragmentDetailsBinding
  private val args: DetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

    binding.apply {
      if (args.movie.backdrop != null) imageView.load("https://image.tmdb.org/t/p/w780${args.movie.backdrop}")
      else imageView.load("https://image.tmdb.org/t/p/w780${args.movie.poster}")
      titleTv.text = args.movie.title
      detailsTv.text = args.movie.description
    }

    return binding.root
  }
}