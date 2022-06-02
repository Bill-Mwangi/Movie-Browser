package com.bill.moviebrowser.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bill.moviebrowser.MovieAdapter
import com.bill.moviebrowser.room.Movie
import com.bill.moviebrowser.viewmodel.MovieViewModel
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.FragmentMainBinding

class MainFragment : Fragment(), SearchView.OnQueryTextListener, MovieAdapter.OnItemClickListener {
  private lateinit var binding: FragmentMainBinding
  private val adapter: MovieAdapter by lazy { MovieAdapter(this) }
  private val viewModel: MovieViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentMainBinding.inflate(layoutInflater, container, false)

    //Setting the RecyclerView
    binding.rvMovies.layoutManager = LinearLayoutManager(context)
//    viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    binding.rvMovies.adapter = adapter


//    viewModel.localData.observe(viewLifecycleOwner) {
//      adapter.changeList(it)
//    }

    //TODO: Check if there is a network connection before using the online source
    viewModel.onlineData.observe(viewLifecycleOwner) {
      adapter.changeList(it)
    }

    return binding.root
  }

  override fun onItemClick(position: Int, view: View?) {
//    Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
    val clickedItem: Movie = adapter.movieList[position]

    val action = MainFragmentDirections.navigateToDetailsFragment(clickedItem)
    view?.findNavController()?.navigate(action)
  }
//    val clickedItem = viewModel.localData.value!!.get(position)


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    menu.clear()
    inflater.inflate(R.menu.menu_items, menu)

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