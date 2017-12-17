package com.magdales.spotify

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Lai on 12/16/2017.
 */
data class Song(
        val title: String = " ",
        val singer: String = " ",
        val album: String = " "
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, falgs: Int) {
        parcel.writeString(title)
        parcel.writeString(singer)
        parcel.writeString(album)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }

        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }
    }
}