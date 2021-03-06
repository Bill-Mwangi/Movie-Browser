package com.bill.moviebrowser

import coil.load
import com.bill.moviebrowser.room.Movie
import com.example.moviebrowser.R

class CastAdapter : TileAdapter() {
  private lateinit var castList: List<Movie.Cast>
  override fun onBindViewHolder(holder: CastHolder, position: Int) {
    binding.apply {
      imageview.load("$baseUrl${castList[position].profilePath}") {
        placeholder(R.drawable.ic_launcher_background)
      }
      textview.text = castList[position].name
    }
  }

  override fun getItemCount(): Int {
    return castList.size
  }

  fun changeList(newList: List<Movie.Cast>) {
    castList = newList
    notifyDataSetChanged()
  }
}