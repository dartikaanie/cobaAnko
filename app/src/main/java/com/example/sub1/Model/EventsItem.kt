package com.example.sub1.Model


import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class EventsItem (

    @SerializedName("strEventAlternate")
    var strEventAlternate: String? = null,

    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strEvent")
    var strEvent: String? = null,

    @SerializedName("intRound")
    var intRound: String? = null,

    @SerializedName("dateEvent")
    var dateEventLocal: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strFilename")
    var strFilename: String? = null,

    @SerializedName("strTime")
    internal var strTime: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("imgHome")
    var imgHome: String? = null,

    @SerializedName("imgAway")
    var imgAway: String? = null
): Parcelable  {

    fun getStrTime(): String? {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        var time : String? = null
        if(strTime != null){
          time = dateFormat.parse(strTime).toString().substring(11,20)
        }
        return time
    }

    fun getScoreAway(): String? {
        return intAwayScore ?: "-"
    }

    fun getScoreHome(): String? {
        return intHomeScore ?: "-"
    }

    override fun toString(): String {
        return "EventsItem{" +
                "strEventAlternate = '" + strEventAlternate + '\''.toString() +
                ",idEvent = '" + idEvent + '\''.toString() +
                ",strSport = '" + strSport + '\''.toString() +
                ",strEvent = '" + strEvent + '\''.toString() +
                ",intRound = '" + intRound + '\''.toString() +
                ",dateEventLocal = '" + dateEventLocal + '\''.toString() +
                ",intHomeScore = '" + intHomeScore + '\''.toString() +
                ",strHomeTeam = '" + strHomeTeam + '\''.toString() +
                ",strFilename = '" + strFilename + '\''.toString() +
                ",strTime = '" + strTime + '\''.toString() +
                ",strLeague = '" + strLeague + '\''.toString() +
                ",intAwayScore = '" + intAwayScore + '\''.toString() +
                ",strAwayTeam = '" + strAwayTeam + '\''.toString() +
                ",idLeague = '" + idLeague + '\''.toString() +
                ",imgHome = '" + imgHome + '\''.toString() +
                ",imgAway = '" + imgAway + '\''.toString() +
                "}"
    }
}