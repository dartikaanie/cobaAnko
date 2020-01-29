package com.example.sub1.fitur.DetailTeam.PlayersTeam

import com.example.sub1.Model.Player.PlayerResponse
import com.example.sub1.rest.LigaDataServices
import com.example.sub1.rest.RetrofitInstances
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPlayerTeam (val namaTeam : String) :  PlayersTeamContract.GetPlayerTeamIntractor{
    lateinit var ligaDataService: LigaDataServices

    override fun getPlayerTeam(onFinishedListener: PlayersTeamContract.GetPlayerTeamIntractor.OnFinishedListener) {
        ligaDataService = RetrofitInstances.getClient().create(LigaDataServices::class.java)
         val call = ligaDataService.getPlayersTeam(namaTeam)

        call.enqueue(object : Callback<PlayerResponse> {
            override fun onFailure(call: Call<PlayerResponse>?, t: Throwable?) {
                t?.let { onFinishedListener.onFailure(it) }
            }

            override fun onResponse(call: Call<PlayerResponse>?, response: Response<PlayerResponse>?) {
                val playerList : PlayerResponse? = response?.body()
                playerList?.let { onFinishedListener.onFinished(it) }
            }
        })
    }
}
