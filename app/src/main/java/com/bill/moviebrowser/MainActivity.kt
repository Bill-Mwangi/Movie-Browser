package com.bill.moviebrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.ActivityMainBinding
import com.bill.moviebrowser.fragments.MainFragment

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val mainFrag = MainFragment()

    supportFragmentManager.beginTransaction().apply {
      replace(R.id.main_frag_container, mainFrag)
      commit()
    }
  }
}