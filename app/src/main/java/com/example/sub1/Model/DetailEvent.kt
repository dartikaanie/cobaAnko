package com.example.sub1.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class DetailEvent : Parcelable{

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null

    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String? = null

    @SerializedName("intHomeShots")
    var intHomeShots: String? = null

    @SerializedName("strSport")
    var strSport: String? = null

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = null

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = null

    @SerializedName("intAwayShots")
    var intAwayShots: String? = null

    @SerializedName("strTime")
    internal var strTime: String? = null
    fun getStrTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val time : String = dateFormat.parse(strTime).toString().substring(11,20)
        return time
    }

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = null

    @SerializedName("idLeague")
    var idLeague: String? = null

    @SerializedName("idSoccerXML")
    var idSoccerXML: String? = null

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = null

    @SerializedName("intSpectators")
    var intSpectators: String? = null

    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String? = null

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = null

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = null

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = null

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String? = null

    @SerializedName("idEvent")
    var idEvent: String? = null

    @SerializedName("strAwayFormation")
    var strAwayFormation: String? = null

    @SerializedName("strEvent")
    var strEvent: String? = null

    @SerializedName("intRound")
    var intRound: String? = null

    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String? = null

    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String? = null

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = null

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null

    @SerializedName("dateEvent")
    var dateEvent: String? = null

    @SerializedName("strLeague")
    var strLeague: String? = null

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String? = null

    @SerializedName("strHomeFormation")
    var strHomeFormation: String? = null

    fun getScoreAway(): String? {
        return intAwayScore ?: "-"
    }

    fun getScoreHome(): String? {
        return intHomeScore ?: "-"
    }

    override fun toString(): String {
        return "DetailEvent{" +
                "idAwayTeam = '" + idAwayTeam + '\''.toString() +
                ",strAwayRedCards = '" + strAwayRedCards + '\''.toString() +
                ",intHomeShots = '" + intHomeShots + '\''.toString() +
                ",strSport = '" + strSport + '\''.toString() +
                ",strHomeLineupDefense = '" + strHomeLineupDefense + '\''.toString() +
                ",strDescriptionEN = '" + strDescriptionEN + '\''.toString() +
                ",strAwayLineupSubstitutes = '" + strAwayLineupSubstitutes + '\''.toString() +
                ",intAwayShots = '" + intAwayShots + '\''.toString() +
                ",strTime = '" + strTime + '\''.toString() +
                ",strAwayGoalDetails = '" + strAwayGoalDetails + '\''.toString() +
                ",strAwayLineupForward = '" + strAwayLineupForward + '\''.toString() +
                ",idLeague = '" + idLeague + '\''.toString() +
                ",idSoccerXML = '" + idSoccerXML + '\''.toString() +
                ",strHomeLineupForward = '" + strHomeLineupForward + '\''.toString() +
                ",intSpectators = '" + intSpectators + '\''.toString() +
                ",strHomeRedCards = '" + strHomeRedCards + '\''.toString() +
                ",strHomeLineupGoalkeeper = '" + strHomeLineupGoalkeeper + '\''.toString() +
                ",strHomeGoalDetails = '" + strHomeGoalDetails + '\''.toString() +
                ",strAwayLineupGoalkeeper = '" + strAwayLineupGoalkeeper + '\''.toString() +
                ",strHomeLineupSubstitutes = '" + strHomeLineupSubstitutes + '\''.toString() +
                ",strAwayLineupMidfield = '" + strAwayLineupMidfield + '\''.toString() +
                ",idEvent = '" + idEvent + '\''.toString() +
                ",strAwayFormation = '" + strAwayFormation + '\''.toString() +
                ",strEvent = '" + strEvent + '\''.toString() +
                ",intRound = '" + intRound + '\''.toString() +
                ",strHomeYellowCards = '" + strHomeYellowCards + '\''.toString() +
                ",strAwayYellowCards = '" + strAwayYellowCards + '\''.toString() +
                ",strAwayLineupDefense = '" + strAwayLineupDefense + '\''.toString() +
                ",idHomeTeam = '" + idHomeTeam + '\''.toString() +
                ",intHomeScore = '" + intHomeScore + '\''.toString() +
                ",strHomeTeam = '" + strHomeTeam + '\''.toString() +
                ",dateEvent = '" + dateEvent + '\''.toString() +
                ",strLeague = '" + strLeague + '\''.toString() +
                ",strAwayTeam = '" + strAwayTeam + '\''.toString() +
                ",intAwayScore = '" + intAwayScore + '\''.toString() +
                ",strHomeLineupMidfield = '" + strHomeLineupMidfield + '\''.toString() +
                ",strHomeFormation = '" + strHomeFormation + '\''.toString() +
                "}"
    }
}