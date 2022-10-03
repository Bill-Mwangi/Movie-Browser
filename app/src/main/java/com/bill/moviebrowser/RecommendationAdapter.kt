package com.bill.moviebrowser

import coil.load
import com.example.moviebrowser.R

class RecommendationAdapter : TileAdapter() {
  private lateinit var movieList: List<MovieDto>

  override fun onBindViewHolder(holder: CastHolder, position: Int) {
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
