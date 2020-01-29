package com.example.sub1.Model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TeamsItem(

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strCountry")
    var strCountry: String? = null,

    @SerializedName("strTeamLogo")
    var strTeamLogo: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("intLoved")
    var intLoved: String? = null,

    @SerializedName("intFormedYear")
    var intFormedYear: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null
) : Parcelable  {


    override fun toString(): String {
        return "TeamsItem{" +
                "strTeam = '" + strTeam + '\''.toString() +
                ",strCountry = '" + strCountry + '\''.toString() +
                ",strTeamLogo = '" + strTeamLogo + '\''.toString() +
                ",strLeague = '" + strLeague + '\''.toString() +
                ",idTeam = '" + idTeam + '\''.toString() +
                ",intLoved = '" + intLoved + '\''.toString() +
                ",strDescriptionEN = '" + strDescriptionEN + '\''.toString() +
                "}"
    }
}