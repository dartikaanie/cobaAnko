package com.example.sub1.rest

import com.example.sub1.BuildConfig

object TheSportDB {
    fun getDetailLiga(IdLiga: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupleague.php?id=" + IdLiga
    }
}