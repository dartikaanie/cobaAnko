package com.example.sub1.fitur.DetailTeam.PlayersTeam

import com.example.sub1.Model.Player.PlayerResponse

interface PlayersTeamContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface DetailPlayerTeamView {
        fun showProgress()
        fun hideProgress()
        fun setDataTeam(playerList: PlayerResponse)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetPlayerTeamIntractor {

        interface OnFinishedListener {
            fun onFinished(playerList: PlayerResponse)
            fun onFailure(t: Throwable)
        }

        fun getPlayerTeam(onFinishedListener: OnFinishedListener)
    }

}