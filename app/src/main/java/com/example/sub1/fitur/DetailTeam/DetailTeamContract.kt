package com.example.sub1.fitur.DetailTeam

import com.example.sub1.Model.TeamsItem

interface DetailTeamContract {

    interface Presenter {
        fun onDestroy()
        fun onRefreshButtonClick()
        fun requestDataFromServer()
    }

    interface DetailTeamView {
        fun showProgress()
        fun hideProgress()
        fun setDataTeam(teamItem: TeamsItem)
        fun onResponseFailure(throwable: Throwable)
    }

    interface GetDetailTeamIntractor {

        interface OnFinishedListener {
            fun onFinished(team: TeamsItem)
            fun onFailure(t: Throwable)
        }

        fun getDetailTeam(onFinishedListener: OnFinishedListener)
    }

}