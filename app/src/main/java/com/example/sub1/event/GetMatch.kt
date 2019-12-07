package com.example.sub1.event

import android.util.Log
import com.example.sub1.Model.EventResponse
import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.TeamList
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMatch (val idLiga : String):  EventListContract.GetNextMatch, EventListContract.GetPrevMatch {

    lateinit var ligaDataService: LigaDataServices;

    override fun getPrevMatch(onFinishedListener: EventListContract.GetPrevMatch.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient()?.create(LigaDataServices::class.java)!!
        val call = ligaDataService.getPrevEvents(idLiga)

        /**Log the URL called*/


        call.enqueue(object : Callback<EventResponse> {
            override fun onFailure(call: Call<EventResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) };
            }

            override fun onResponse(call: Call<EventResponse>?, response: Response<EventResponse>?) {
                var event : EventResponse? = response?.body()
                val x: List<EventsItem>? = event?.events
                if (x != null) {
                    for(item in x){
                        lateinit var imgHomeTeam : String
                        val callHome = ligaDataService.getTeams(item.idHomeTeam.toString())
                        callHome.enqueue(object : Callback<TeamList> {
                            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {

                            }
                            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                                imgHomeTeam = response?.body()?.teams?.get(0)?.strTeamLogo.toString()
                                item.imgHome = imgHomeTeam
                            }
                        });

                        lateinit var imgAwayTeam : String
                        val callAway = ligaDataService.getTeams(item.idAwayTeam.toString())
                        callAway.enqueue(object : Callback<TeamList> {
                            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {

                            }
                            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                                imgAwayTeam = response?.body()?.teams?.get(0)?.strTeamLogo.toString()
                                item.imgAway = imgAwayTeam
                                event?.let { onFinishedListener.onFinishedPrev(it) }
                            }
                        });
                    }

                }


            }
        });
    }

    override fun getNextMatch(onFinishedListener: EventListContract.GetNextMatch.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient()?.create(LigaDataServices::class.java)!!
        val call = ligaDataService.getNextEvents(idLiga)

        /**Log the URL called*/
        Log.e("URL Called", call.request().url().toString());

        call.enqueue(object : Callback<EventResponse> {
            override fun onFailure(call: Call<EventResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) };
            }

            override fun onResponse(call: Call<EventResponse>?, response: Response<EventResponse>?) {
                var event : EventResponse? = response?.body()
                val x: List<EventsItem>? = event?.events
                var list: MutableList<EventsItem> = ArrayList()
                if (x != null) {
                    for(item in x){
                        lateinit var imgHomeTeam : String
                        val callHome = ligaDataService.getTeams(item.idHomeTeam.toString())
                        callHome.enqueue(object : Callback<TeamList> {
                            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {

                            }
                            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                                imgHomeTeam = response?.body()?.teams?.get(0)?.strTeamLogo.toString()
                                item.imgHome = imgHomeTeam
                            }
                        });

                        lateinit var imgAwayTeam : String
                        val callAway = ligaDataService.getTeams(item.idAwayTeam.toString())
                        callAway.enqueue(object : Callback<TeamList> {
                            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {

                            }
                            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                                imgAwayTeam = response?.body()?.teams?.get(0)?.strTeamLogo.toString()
                                item.imgAway = imgAwayTeam
                                event?.let { onFinishedListener.onFinishedNext(it) }
                            }
                        });
                    }

                }
//                event?.let { onFinishedListener.onFinishedNext(it) };
            }
        });
    }



}