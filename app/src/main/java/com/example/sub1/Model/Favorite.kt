package com.example.sub1.Model

import java.text.SimpleDateFormat
import java.util.*

data class Favorite(
    val idEvent: String?,
    val strSport: String? = null,
    val strEvent: String? = null,
    val dateEventLocal: String? = null,
    val idHomeTeam: String? = null,
    val intHomeScore: String? = null,
    val strHomeTeam: String? = null,
    internal var strTime: String? = null,
    val strLeague: String? = null,
    val strAwayTeam: String? = null,
    val intAwayScore: String? = null,
    val idLeague: String? = null,
    val imgHome: String? = null,
    val imgAway: String? = null
    ) {

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


    companion object {
        const val TABLE_FAVORITE_EVENT: String = "TABLE_FAVORITE_EVENT"
        const val EVENT_ID: String = "EVENT_ID"
        const val STR_SPORT: String = "STR_SPORT"
        const val STR_EVENT: String = "STR_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val STR_TIME: String = "STR_TIME"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val STR_LEAGUE: String = "STR_LEAGUE"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val IMG_HOME: String = "IMG_HOME"
        const val IMG_AWAY: String = "IMG_AWAY"
    }
}