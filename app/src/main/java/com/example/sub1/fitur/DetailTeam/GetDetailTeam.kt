package com.example.sub1.fitur.DetailTeam

import com.example.sub1.Model.TeamList
import com.example.sub1.Model.TeamsItem
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetDetailTeam (val idTeam : String) :
    DetailTeamContract.GetDetailTeamIntractor{
    lateinit var ligaDataService: LigaDataServices
    override fun getDetailTeam(onFinishedListener: DetailTeamContract.GetDetailTeamIntractor.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient().create(LigaDataServices::class.java)
        val call = ligaDataService.getDetailTeam(idTeam)

        call.enqueue(object : Callback<TeamList> {
            override fun onFailure(call: Call<TeamList>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) }
            }

            override fun onResponse(call: Call<TeamList>?, response: Response<TeamList>?) {
                val teamList : TeamList? = response?.body()
                val teamItem : TeamsItem? = teamList?.teams?.get(0)
                teamItem?.let { onFinishedListener.onFinished(it) }
            }
        })
    }
}
