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

    mainFragment = MainFragment()
    supportFragmentManager.beginTransaction().apply {
      replace(R.id.main_frag_container, mainFragment)
      commit()
    }

    //Todo: Add the drawer layout buttons to the activity and implement onClickListener
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_items, menu)

//    val search = menu?.findItem(R.id.search_button)
//    val searchView = search?.actionView as? SearchView
//    searchView?.isSubmitButtonEnabled = true
//    searchView?.setOnQueryTextListener(this)
    return true
  }

//  override fun onQueryTextChange(newText: String?): Boolean {
//    mainFragment.
//  }
//
//  override fun onQueryTextSubmit(query: String?): Boolean {
//    TODO("Not yet implemented")
//  }
}