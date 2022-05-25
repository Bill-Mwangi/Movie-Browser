package com.bill.moviebrowser

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bill.moviebrowser.fragments.MainFragment
import com.example.moviebrowser.R
import com.example.moviebrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private lateinit var mainFragment: MainFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    //Todo: Add the drawer layout buttons to the activity and implement onClickListener
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_items, menu)
    return true
  }
}