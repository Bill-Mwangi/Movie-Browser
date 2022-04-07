package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bill.moviebrowser.MovieAdapter
import com.bill.moviebrowser.TVDB
import com.example.moviebrowser.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
  private lateinit var binding: FragmentMainBinding
  private lateinit var adapter: MovieAdapter
  @Volatile private lateinit var page: TVDB.Page

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)

    //Setting the RecyclerView
    binding.rvMovies.layoutManager = LinearLayoutManager(context)
    adapter = MovieAdapter()
    binding.rvMovies.adapter = adapter
    val tvdb = TVDB()

    viewLifecycleOwner.lifecycleScope.launch {
      page = tvdb.getPopularMovies()
      adapter.changeList(page.movies)
      }

    return binding.root
  }
}