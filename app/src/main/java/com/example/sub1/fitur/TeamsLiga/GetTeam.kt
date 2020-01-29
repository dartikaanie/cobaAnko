package com.example.sub1.fitur.TeamsLiga

import com.example.sub1.Model.TeamList
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetTeam (val idLiga : String) :
    TeamsLigaContract.GetTeamIntractor {

    lateinit var ligaDataService: LigaDataServices

    override fun getTeamList(onFinishedListener: TeamsLigaContract.GetTeamIntractor.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient().create(LigaDataServices::class.java)

        val call = ligaDataService.getLigaTeams(idLiga)

        call.enqueue(object : Callback<TeamList> {
            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) }
            }

            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                var teamList : TeamList? = response?.body()
                teamList?.let { onFinishedListener.onFinished(it) }
            }
        })
    }
}
