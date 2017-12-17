package com.magdales.spotify

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.util.SparseBooleanArray



/**
 * Created by Lai on 12/16/2017.
 */
class CustomAdapter(val playlist: ArrayList<Song>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var isClicked = true
    private val sSelectedItems: SparseBooleanArray? = null
    companion object {
        val key = "123"
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val song: Song = playlist[position]
        holder?.txtTitle?.text = playlist[position].title
        holder?.txtSinger?.text = playlist[position].singer
        holder?.txtAlbum?.text = playlist[position].album

        holder?.cardView?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (isClicked) {
                    holder?.txtTitle.setTextColor(Color.parseColor("#1DB954"));
                } else {
                    holder?.txtTitle.setTextColor(Color.parseColor("#fff"));
                    isClicked = false
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context).inflate(R.layout.song_layout, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return playlist.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        val txtSinger = itemView.findViewById<TextView>(R.id.txtSinger)
        val txtAlbum = itemView.findViewById<TextView>(R.id.txtAlbum)
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
    }
}