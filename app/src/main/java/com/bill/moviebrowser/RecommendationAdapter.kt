package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.TileBinding

class RecommendationAdapter: RecyclerView.Adapter<RecommendationAdapter.RecommendationsHolder>() {
  private var movieList: List<MovieDto> = emptyList()
  private lateinit var binding: TileBinding
  private val baseUrl = "https://image.tmdb.org/t/p/w92"

  inner class RecommendationsHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsHolder {
    binding = TileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return RecommendationsHolder(binding.root)
  }


  override fun getItemViewType(position: Int): Int {
    return position
  }

  override fun onBindViewHolder(holder: RecommendationsHolder, position: Int) {
    binding.apply {
      imageview.load("$baseUrl${movieList[position].poster}") {
        placeholder(R.drawable.ic_launcher_background)
      }
      textview.text = movieList[position].title
    }
  }

  override fun getItemCount(): Int {
    return movieList.size
  }

  fun changeList(newList: List<MovieDto>) {
    movieList = newList
    notifyDataSetChanged()
  }
}
