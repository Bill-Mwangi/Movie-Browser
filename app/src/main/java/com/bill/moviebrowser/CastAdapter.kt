package com.bill.moviebrowser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.TileBinding

class CastAdapter(private val listener: OnItemClickListener) :
  RecyclerView.Adapter<CastAdapter.CastHolder>() {
  private var castList = emptyList<CastDto>()
  private lateinit var binding: TileBinding
  private val baseUrl = "https://image.tmdb.org/t/p/w92"

  inner class CastHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    init {
      itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
      if (adapterPosition != RecyclerView.NO_POSITION) listener.onCastItemClick(
        adapterPosition,
        view
      )
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
    binding = TileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CastHolder(binding.root)
  }

  override fun getItemViewType(position: Int): Int {
    return position
  }

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

  interface OnItemClickListener {
    fun onCastItemClick(position: Int, view: View?)
  }
}