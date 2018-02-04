package com.magdales.spotify

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


/**
 * Created by Lai on 12/16/2017.
 */
class CustomAdapter(val musicList: ArrayList<Song>, val context: Context, val mainActivity: MainActivity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var mContext = context
    var allMusicList: ArrayList<String> = ArrayList()
    companion object {
        val PLAYLIST = "key-playlist"
        val POSITION = "pos"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.song_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var playlist: Song = musicList[position]

        holder!!.mTitle.text = playlist.songTitle
        holder.mArtist.text = playlist.songArtist

        holder.setOnCustomItemClickListener(object : CustomItemClickListener {
            override fun onCustomItemClick(view: View, pos: Int) {

                var status = true;

                for (i in 0 until musicList.size) {
                    allMusicList.add(musicList[i].songPath)
                }

                try {
                    val fragment = Fragment.newInstance(playlist.songTitle, playlist.songArtist, status)
                    mainActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment, fragment)
                            .addToBackStack(null)
                            .commit()
                } catch (e: Exception) {
                    Toast.makeText(mainActivity, "Error!", Toast.LENGTH_SHORT)
                }

                var musicData = Intent(mContext, MusicPlayerService::class.java)
                musicData.putStringArrayListExtra(PLAYLIST, allMusicList)
                musicData.putExtra(POSITION, pos)
                musicData.setAction(Key.MUSICPLAY)
                mContext.startService(musicData)
            }
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var rLayout: RelativeLayout
        var mTitle: TextView
        var mArtist: TextView
        var mCategory: TextView
        var mCustomItemClickListener: CustomItemClickListener? = null

        init {
            rLayout = itemView.findViewById(R.id.recyclerView)
            mTitle = itemView.findViewById(R.id.txtTitle)
            mArtist = itemView.findViewById(R.id.txtSinger)
            mCategory = itemView.findViewById(R.id.txtAlbum)
            itemView.setOnClickListener(this)
        }

        fun setOnCustomItemClickListener(customItemClickListener: CustomItemClickListener) {
            this.mCustomItemClickListener = customItemClickListener
        }

        override fun onClick(view: View?) {
            this.mCustomItemClickListener!!.onCustomItemClick(view!!, adapterPosition)
        }
    }

}