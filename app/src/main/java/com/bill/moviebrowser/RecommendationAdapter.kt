package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.TileBinding

class RecommendationAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<RecommendationAdapter.RecommendationsHolder>() {
  var movieList = emptyList<MovieDto>()
  private lateinit var binding: TileBinding
  private val baseUrl = "https://image.tmdb.org/t/p/w92"

  inner class RecommendationsHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
      itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
      if (adapterPosition != RecyclerView.NO_POSITION) listener.onItemClick(adapterPosition, view)
    }
  }

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

  interface OnItemClickListener {
    fun onItemClick(position: Int, view: View?)
  }
}
