package com.magdales.spotify

import android.app.ActionBar
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.Menu


class MainActivity : AppCompatActivity() {

    var isFragmentLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar()?.setDisplayShowCustomEnabled(true);
        getSupportActionBar()?.setCustomView(R.layout.custom_action_bar);

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val playlist = ArrayList<Song>()

        playlist.add(Song("Titibo-tibo", "Moira Dela Torre", "Himig Handog 2017"))
        playlist.add(Song("Havana", "Camila Cabello, Young Thug", "Havana"))
        playlist.add(Song("Young Dumb & Broke", "Khalid", "American Teen"))
        playlist.add(Song("What Lovers Do (feat. SZA)", "Maroon 5, SZA", "Red Pill Blues (Deluxe)"))
        playlist.add(Song("Perfect", "Ed Sheeran", "÷ (Deluxe)"))
        playlist.add(Song("Super Far", "LANY", "LANY"))
        playlist.add(Song("Too Good At Goodbyes", "Sam Smith", "The Thrill of It All"))

        var adapter = CustomAdapter(playlist)
        rv.adapter = adapter
    }

    fun showFragment() {
        val transaction = manager.beginTransaction()
        val fragment = Fragment()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentLoaded = true
    }
}

