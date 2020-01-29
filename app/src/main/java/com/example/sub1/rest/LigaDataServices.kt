package com.example.sub1.rest

import com.example.sub1.BuildConfig
import com.example.sub1.Model.*
import com.example.sub1.Model.Player.PlayerResponse
import com.example.sub1.Model.klasemen.KlasemenResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface LigaDataServices{

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupleague.php")
    fun getDetailLiga(@Query("id") idLiga: String): Call<LigaResponse>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookuptable.php")
    fun getKlasemen(@Query("l") idLiga: String): Call<KlasemenResponse>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php")
    fun getLigaTeams(@Query("id") idLiga: String): Call<TeamList>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php")
    fun getTeams(@Query("id") idTeam: String): Call<TeamList>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php?")
    fun getDetailTeam(@Query("id") idTeam: String): Call<TeamList>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/searchplayers.php?")
    fun getPlayersTeam(@Query("t") namaTeam: String): Call<PlayerResponse>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php")
    fun getPrevEvents(@Query("id") idLiga: String): Call<EventResponse>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php")
    fun getNextEvents(@Query("id") idLiga: String): Call<EventResponse>

    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php")
    fun getNextEvents2(@Query("id") idLiga: String): EventResponse


    @GET( "api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php")
    fun getDetailEvent(@Query("id") idEvent: String): Call<DetailEventResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php")
    fun getEvent(@Query("e") id:String) : Call<MatchResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php")
    fun getTeamGeneral(@Query("t") id:String) : Call<TeamList>
}