package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bill.moviebrowser.CastDto
import com.example.moviebrowser.databinding.FragmentCastBinding

class CastFragment : Fragment() {
  private lateinit var binding: FragmentCastBinding
  private val args: CastFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCastBinding.inflate(layoutInflater, container, false)

    val cast: CastDto = args.cast
    //Todo: Display cast details

    return binding.root
  }
}