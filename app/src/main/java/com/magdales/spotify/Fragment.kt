package com.magdales.spotify

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Lai on 12/17/2017.
 */

class Fragment : Fragment() {

    private var mMusicTitle: TextView? = null
    private var mArtist: TextView? = null
    private var mStatus: ImageView? = null

    companion object {

        fun newInstance(song: String, artist: String, status: Boolean): Fragment {

            val args = Bundle()
            args.putString(Key.TITLE, song)
            args.putString(Key.ARTIST, artist)
            args.putBoolean(Key.STATUS, status)
            val fragment = Fragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_layout, container, false)

        mMusicTitle = view!!.findViewById<TextView>(R.id.txtTitle) as TextView
        mArtist = view.findViewById<TextView>(R.id.txtSinger) as TextView

        val music = arguments.getString(Key.TITLE)
        val artist = arguments.getString(Key.ARTIST)
        var status = arguments.getBoolean(Key.STATUS)

        if (music != null && artist != null) {
            mMusicTitle!!.text = music
            mArtist!!.text = artist
        }

        mStatus!!.setOnClickListener {
            if (status) {
                var songIntent = Intent(context, MusicPlayerService::class.java).apply {
                    action = Key.PAUSE
                }
                context.startService(songIntent)
                mStatus!!.setImageResource(R.drawable.pause_icon)
                status = false
            } else {
                var songIntent = Intent(context, MusicPlayerService::class.java).apply {
                    action = Key.RESUME
                }
                context.startService(songIntent)
                mStatus!!.setImageResource(R.drawable.play_icon)
                status = true
            }
        }

        return view
    }
}