package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebrowser.databinding.TileBinding

abstract class TileAdapter : RecyclerView.Adapter<TileAdapter.CastHolder>() {
  lateinit var binding: TileBinding
  val baseUrl = "https://image.tmdb.org/t/p/w92"

  inner class CastHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
    binding = TileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CastHolder(binding.root)
  }


  override fun getItemViewType(position: Int): Int {
    return position
  }


}