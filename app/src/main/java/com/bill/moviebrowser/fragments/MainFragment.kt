package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bill.moviebrowser.MovieAdapter
import com.bill.moviebrowser.room.MovieViewModel
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.FragmentMainBinding

class MainFragment : Fragment(), SearchView.OnQueryTextListener {
  private lateinit var binding: FragmentMainBinding
  private val adapter: MovieAdapter by lazy { MovieAdapter() }
  private lateinit var viewModel: MovieViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)

    //Setting the RecyclerView
    binding.rvMovies.layoutManager = LinearLayoutManager(context)
    viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    binding.rvMovies.adapter = adapter

    viewModel.localData.observe(viewLifecycleOwner) {
      adapter.changeList(it)
    }

    return binding.root
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    val search = menu.findItem(R.id.search_button)
    val searchView = search?.actionView as? SearchView
    searchView?.isSubmitButtonEnabled = true
    searchView?.setOnQueryTextListener(this)
  }

  override fun onQueryTextChange(query: String?): Boolean {
    if (query != null) searchDatabase(query)
    return true
  }

  override fun onQueryTextSubmit(query: String?): Boolean {
    return true
  }

  private fun searchDatabase(query: String) {
    val searchQuery = "%$query%"

    viewModel.searchDatabase(searchQuery).observe(this) { list ->
      list.let {
        adapter.changeList(it)
      }
    }
  }
}