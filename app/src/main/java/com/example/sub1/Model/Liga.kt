package com.example.sub1.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize

@Parcelize
class Liga(
    @SerializedName("idLeague")
    var id: String? = null,

    @SerializedName("strLeague")
    var name: String? = null,

    @SerializedName("strLogo")
    var image: String? = null,

    @SerializedName("strDescriptionEN")
    var deskripsi: String? = ""
    ) : Parcelable {

    override fun toString(): String {
        return "LeaguesItem{" +
                "strLogo = '" + image + '\''.toString() +
                ",strLeague = '" + name + '\''.toString() +
                ",idLeague = '" + id + '\''.toString() +
                ",strDescriptionEN = '" + deskripsi + '\''.toString() +
                "}"
    }
}