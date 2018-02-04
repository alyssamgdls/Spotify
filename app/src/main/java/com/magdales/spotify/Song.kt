package com.magdales.spotify

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Lai on 12/16/2017.
 */

data class Song(val songTitle: String = "", val songArtist: String = "", val songPath: String = "", val songAlbum: String = "", var isSelected: Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(songTitle)
        parcel.writeString(songArtist)
        parcel.writeString(songPath)
        parcel.writeString(songAlbum)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }

}