package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bill.moviebrowser.MovieAdapter
import com.bill.moviebrowser.room.MovieViewModel
import com.example.moviebrowser.databinding.FragmentMainBinding

class MainFragment : Fragment() {
  private lateinit var binding: FragmentMainBinding
  private lateinit var adapter: MovieAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)

    //Setting the RecyclerView
    binding.rvMovies.layoutManager = LinearLayoutManager(context)
    val viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    adapter = MovieAdapter()
    binding.rvMovies.adapter = adapter

    viewModel.localData.observe(viewLifecycleOwner) {
      adapter.changeList(it)
    }

    return binding.root
  }
}