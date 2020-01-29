package com.example.sub1.fitur.listPertandingan

import com.example.sub1.Model.EventsItem
import com.example.sub1.Model.MatchResponse
import com.example.sub1.Model.TeamList
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetEvent(val id : String):
    PertandinganContract.GetEvent {
    lateinit var ligaDataService: LigaDataServices
    override fun getEvent(onFinishedListener: PertandinganContract.GetEvent.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient().create(LigaDataServices::class.java)
        val call = ligaDataService.getEvent(id)

       call.enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) }
            }

            override fun onResponse(call: Call<MatchResponse>?, response: Response<MatchResponse>?) {
                var event : MatchResponse? = response?.body()
                val x: List<EventsItem>? = event?.event?.filter { it.strSport == "Soccer" }
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
                        })

                        lateinit var imgAwayTeam : String
                        val callAway = ligaDataService.getTeams(item.idAwayTeam.toString())
                        callAway.enqueue(object : Callback<TeamList> {
                            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {

                            }
                            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                                imgAwayTeam = response?.body()?.teams?.get(0)?.strTeamLogo.toString()
                                item.imgAway = imgAwayTeam
                                    event?.let { onFinishedListener.onFinished(it) }


                            }
                        })
                    }

                }else{
                    onFinishedListener.noData()
                }
//                event?.let { onFinishedListener.onFinishedNext(it) };
            }
        })
    }
}