package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.CastBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastHolder>() {
  var castList: List<TVDB.Cast> = emptyList()
  private lateinit var binding: CastBinding
  private val baseUrl = "https://image.tmdb.org/t/p/w92"

  inner class CastHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
    binding = CastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CastHolder(binding.root)
  }

  override fun onBindViewHolder(holder: CastHolder, position: Int) {
    binding.apply {
      castImg.load("$baseUrl${castList[position].profilePath}") {
        placeholder(R.drawable.ic_launcher_background)
      }
      castNameTv.text = "${castList[position].name} as ${castList[position].character}"
    }
  }

  override fun getItemCount(): Int {
    return castList.size
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

  fun changeList(newList: List<TVDB.Cast>) {
    castList = newList
    notifyDataSetChanged()
  }
}