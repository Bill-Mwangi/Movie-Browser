package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bill.moviebrowser.room.Movie
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.MovieItemBinding

class MovieAdapter(private val listener: OnItemClickListener) :
  RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
  var movieList = emptyList<Movie>()
  private lateinit var binding: MovieItemBinding
  private lateinit var url: String
  private val baseUrl = "https://image.tmdb.org/t/p/w92"

  inner class MovieViewHolder(itemView: View) : View.OnClickListener,
    RecyclerView.ViewHolder(itemView) {

    init {
      itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
      if (adapterPosition != RecyclerView.NO_POSITION) listener.onItemClick(adapterPosition, v)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MovieViewHolder(binding.root)
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    binding.apply {
      url = "$baseUrl${movieList[position].poster}"
      imgPoster.load(url) {
        placeholder(R.drawable.ic_launcher_background)
      }
      tvTitle.text = movieList[position].title
      tvDescription.text = movieList[position].description
    }
  }

  override fun getItemCount(): Int {
    return movieList.size
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  fun changeList(newList: List<Movie>) {
    movieList = newList
    notifyDataSetChanged()
  }

  interface OnItemClickListener {
    fun onItemClick(position: Int, view: View?)
  }
}