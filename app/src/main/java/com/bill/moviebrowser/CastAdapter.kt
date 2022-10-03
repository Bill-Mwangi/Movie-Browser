package com.bill.moviebrowser

import coil.load
import com.example.moviebrowser.R

class CastAdapter : TileAdapter() {
  private var castList = emptyList<CastDto>()
  override fun onBindViewHolder(holder: CastHolder, position: Int) {
    binding.apply {
      imageview.load("$baseUrl${castList[position].profile}") {
        placeholder(R.drawable.ic_launcher_background)
      }
      textview.text = castList[position].name
    }
  }

  override fun getItemCount(): Int {
    return castList.size
  }

  fun changeList(newList: List<CastDto>) {
    castList = newList
    notifyDataSetChanged()
  }
}