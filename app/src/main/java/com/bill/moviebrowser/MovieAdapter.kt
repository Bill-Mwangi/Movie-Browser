package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bill.moviebrowser.room.Movie
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.MovieItemBinding

class MovieAdapter(private var list: List<Movie>) :
  RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
  private lateinit var binding: MovieItemBinding
  private lateinit var url: String
  private val baseUrl = "https://image.tmdb.org/t/p/w92"

  constructor() : this(emptyList<Movie>())

  constructor(livedata: LiveData<List<Movie>>) : this(livedata.value ?: emptyList())

  inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

//  inner class AdapterObserver() : RecyclerView.AdapterDataObserver() {
//
//  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MovieViewHolder(binding.root)
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    binding.apply {
      url = "$baseUrl${list[position].imagePath}"
      imgPoster.load(url) {
        placeholder(R.drawable.ic_launcher_background)
      }
      tvTitle.text = list[position].title
      tvDescription.text = list[position].description
    }
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  fun changeList(newList: List<Movie>) {
    this.list = newList
    notifyDataSetChanged()
  }

  // FIXME: Add a function to change the list upon changes in the livedata
}